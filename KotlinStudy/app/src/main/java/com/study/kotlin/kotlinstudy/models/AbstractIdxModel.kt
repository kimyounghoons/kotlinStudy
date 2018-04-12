package com.study.kotlin.kotlinstudy.models

import android.os.Parcel
import android.os.Parcelable
import com.study.kotlin.kotlinstudy.Observable


/**
 * Created by kimyounghoon on 2018-04-06.
 */
open class AbstractIdxModel(var Idx: String) : Observable(), Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Idx)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AbstractIdxModel> {
        override fun createFromParcel(parcel: Parcel): AbstractIdxModel {
            return AbstractIdxModel(parcel)
        }

        override fun newArray(size: Int): Array<AbstractIdxModel?> {
            return arrayOfNulls(size)
        }
    }
}