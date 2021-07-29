package ru.samitin.allmovies.model.repository

import android.content.res.Resources
import ru.samitin.allmovies.R
import ru.samitin.allmovies.model.data.Movie

class RepositoryImpl(val resources:Resources) :Repository{

    private val movies:List<Movie>
    init {
        val names=resources.getStringArray(R.array.titles);
        val ratings=resources.getIntArray(R.array.ratings)
        val dates=resources.getStringArray(R.array.dates)
        movies= ArrayList<Movie>()
        for (i in 0..(names.size-1))
            movies+Movie(name = names[i],image = i,reting = ratings[i],date=dates[i])
    }
    override fun getMoviesFromLocalStorage(): List<Movie> = movies
    override fun getMoviesFromServer(): List<Movie> = movies

}