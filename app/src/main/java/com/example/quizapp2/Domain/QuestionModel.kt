package com.example.quizapp2.Domain

import android.os.Parcel
import android.os.Parcelable

data class QuestionModel(
    val id: Int,
    val question: String?,
    val firstOption: String?,
    val secondOption: String?,
    val thirdOption: String?,
    val fourthOption: String?,
    val answer: String?,
    val score: Int,
    val ivPath: String?,
    var clickedAnswer: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(question)
        parcel.writeString(firstOption)
        parcel.writeString(secondOption)
        parcel.writeString(thirdOption)
        parcel.writeString(fourthOption)
        parcel.writeString(answer)
        parcel.writeInt(score)
        parcel.writeString(ivPath)
        parcel.writeString(clickedAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionModel> {
        override fun createFromParcel(parcel: Parcel): QuestionModel {
            return QuestionModel(parcel)
        }

        override fun newArray(size: Int): Array<QuestionModel?> {
            return arrayOfNulls(size)
        }
    }
}
