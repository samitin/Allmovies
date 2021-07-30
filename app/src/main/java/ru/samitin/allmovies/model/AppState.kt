package ru.samitin.allmovies.model

import ru.samitin.allmovies.model.data.Category
import ru.samitin.allmovies.model.data.Movie

sealed class AppState{
    data class Success(val listCategory:List<Category>): AppState()
    data class Error(val error: Unit): AppState()
    object Loading: AppState()
}
