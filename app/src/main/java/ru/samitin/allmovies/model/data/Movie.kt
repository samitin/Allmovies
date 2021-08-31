package ru.samitin.allmovies.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val overview:String?,val release_date:String?,val title:String?,val vote_average:Double?,var image:Int?):Parcelable
