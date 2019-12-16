package es.izaguirre.examen_izaguirre_alejandra.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event( val name: String, val place: String, val dateTime: String, val speaker: String, val description: String): Parcelable {}