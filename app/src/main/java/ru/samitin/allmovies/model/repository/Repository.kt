package ru.samitin.allmovies.model.repository

import ru.samitin.allmovies.model.data.Movie;
interface Repository {
    fun getMoviesFromServer(): List<Movie>
    fun getMoviesFromLocalStorage(): List<Movie>
}