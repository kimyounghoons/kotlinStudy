package com.study.kotlin.kotlinstudy.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by younghoon on 2018-04-03.
 */
class User(idx : String, var name: String = "kim") : AbstractIdxModel(idx) , Parcelable//아래는 같은 방법
{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel,flags)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
//class User : AbstractIdxModel {
//    var name: String
//
//    constructor(idx: String, name: String = "kim") : super(idx) {
//        this.name = name
//    }
//}