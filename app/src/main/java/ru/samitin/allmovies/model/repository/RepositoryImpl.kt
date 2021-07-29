package ru.samitin.allmovies.model.repository

import android.content.res.Resources
import android.content.res.TypedArray
import ru.samitin.allmovies.R
import ru.samitin.allmovies.model.data.Movie

class RepositoryImpl(val resources:Resources= Resources.getSystem()) :Repository{

    private val movies:List<Movie>
    init {
        val names=resources.getStringArray(R.array.titles);
        val ratings=resources.getIntArray(R.array.ratings)
        val dates=resources.getStringArray(R.array.dates)
        movies= ArrayList<Movie>()
        val images:TypedArray =resources.obtainTypedArray(R.array.images)
        for (i in 0..(names.size-1))
            movies+Movie(name = names[i],images.getResourceId(i,0),reting = ratings[i],date=dates[i])
    }
    override fun getMoviesFromLocalStorage(): List<Movie> = movies
    override fun getMoviesFromServer(): List<Movie> = movies

}