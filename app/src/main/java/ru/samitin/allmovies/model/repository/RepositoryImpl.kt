package ru.samitin.allmovies.model.repository

import android.annotation.SuppressLint
import android.content.res.Resources
import android.content.res.TypedArray
import ru.samitin.allmovies.R
import ru.samitin.allmovies.model.data.Movie

@SuppressLint("ResourceType")
class RepositoryImpl(val resources:Resources= Resources.getSystem()) :Repository{

    private val movies:List<Movie>
    init {
        val names=resources.getStringArray(R.array.titles);
        val ratings=resources.getIntArray(R.array.ratings)
        val dates=resources.getStringArray(R.array.dates)
        val images:TypedArray =resources.obtainTypedArray(R.array.images)
        movies= listOf<Movie>(Movie(name = names[0],images.getResourceId(0,0),reting = ratings[0],date=dates[0]),
                Movie(name = names[1],images.getResourceId(1,0),reting = ratings[1],date=dates[1]),
                Movie(name = names[2],images.getResourceId(2,0),reting = ratings[2],date=dates[2]),
                Movie(name = names[3],images.getResourceId(3,0),reting = ratings[3],date=dates[3]),
                Movie(name = names[4],images.getResourceId(4,0),reting = ratings[4],date=dates[4]))



    }
    override fun getMoviesFromLocalStorage(): List<Movie> = movies
    override fun getMoviesFromServer(): List<Movie> = movies

}