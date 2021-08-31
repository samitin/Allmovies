package ru.samitin.allmovies.model.dto

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import org.json.JSONObject
import ru.samitin.allmovies.BuildConfig
import ru.samitin.allmovies.BuildConfig.*
import ru.samitin.allmovies.model.data.Category
import ru.samitin.allmovies.model.data.Movie
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection
import com.google.gson.reflect.TypeToken as TypeToken


class MoviesLoader {
    private val YOUR_API_KEY = "0b8cdfc64ddf0f42c3b7bbfa666780aa"


    @RequiresApi(Build.VERSION_CODES.N)
    constructor(){
       // this.loadGenre()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    public fun loadGenre() :Map<Int,String>{
        var genres:MutableMap<Int,String> = mutableMapOf()

        try {
            val uri =
                    URL("https://api.themoviedb.org/3/genre/movie/list?api_key=${YOUR_API_KEY}&language=ru")
            Thread (Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val joinText = getLines(bufferedReader)
                    val jsonObject = JSONObject(joinText)
                    var jsonArray = jsonObject.getJSONArray("genres")
                    for (i in 0..jsonArray.length() - 1) {
                        var userData = jsonArray.getJSONObject(i)
                        genres.put(userData.getInt("id"), userData.getString("name"))
                    }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    //Обработка ошибки
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            //Обработка ошибки
        }
        return genres
    }
    @RequiresApi(Build.VERSION_CODES.N)
    public fun loadCategory(id:Int,name:String): Category {

        var list= mutableListOf<Movie>()
        try {
            val uri =
                URL("\n" +
                        "https://api.themoviedb.org/3/list/"+id+"?api_key="+YOUR_API_KEY+"&language=ru")
            val handler = Handler(Looper.myLooper()!!)
            Thread (Runnable {
                lateinit var urlConnection: HttpsURLConnection
                val any = try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val joinText = getLines(bufferedReader)
                    val jsonObject = JSONObject(joinText)
                    var jsonArray = jsonObject.getJSONArray("items")
                    for (i in 0..jsonArray.length() - 1) {
                        var userData = jsonArray.getJSONObject(i)
                        list.add(
                            Movie(
                                overview = userData.getString("overview"),
                               release_date =  userData.getString("release_date"),
                                title = userData.getString("title"),
                                vote_average = userData.getDouble("vote_average"),
                                image = 3
                            )
                        )
                    }
                    handler.post { }
                } catch (e: Exception) {
                    Log.e("TAG", "Fail connection", e)
                    e.printStackTrace()
                    //Обработка ошибки
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("TAG", "Fail URI", e)
            e.printStackTrace()
            //Обработка ошибки
        }
        return  Category(name, list)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getServerCategories():List<Category>{
        var listCategories= mutableListOf<Category>()
        Thread (Runnable {
            val genre = loadGenre()
            for (id in genre.keys) {
                var category = genre.get(id)?.let { loadCategory(id, it) }
                if (category != null) {
                    listCategories.add(category)
                }
            }
        }).start()
        return listCategories
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}