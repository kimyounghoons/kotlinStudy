package com.study.kotlin.kotlinstudy.models


import android.os.Parcel
import android.os.Parcelable
import com.study.kotlin.kotlinstudy.ModelManager

/**
 * Created by younghoon on 2018-04-03.
 */
class Post : AbstractIdxModel, Parcelable {
    var user: User

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            User(parcel))

    constructor(Idx: String, user: User) : super(Idx) {
        this.user = user
        ModelManager.putPost(this)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        user.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return super.describeContents()
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}