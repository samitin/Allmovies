package ru.samitin.allmovies.service

import android.os.Parcel
import android.os.Parcelable

data class CategoryDTO(val id:Int, val name: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryDTO> {
        override fun createFromParcel(parcel: Parcel): CategoryDTO {
            return CategoryDTO(parcel)
        }

        override fun newArray(size: Int): Array<CategoryDTO?> {
            return arrayOfNulls(size)
        }
    }
}