package ru.samitin.allmovies.model.data

import android.content.res.Resources
import android.content.res.TypedArray
import android.os.Parcelable
import ru.samitin.allmovies.R
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Category (val categoryName:String,val movies:List<Movie>):Parcelable