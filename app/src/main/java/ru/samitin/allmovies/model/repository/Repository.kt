package ru.samitin.allmovies.model.repository

import android.content.Context
import android.content.res.Resources
import ru.samitin.allmovies.model.data.Category
import ru.samitin.allmovies.model.data.Movie;
interface Repository {
    fun getMoviesFromServer(): List<Category>
    fun getMoviesFromLocalStorage(): List<Category>
}