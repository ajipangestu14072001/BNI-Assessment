package aji.dev.bniassessment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attributes(
    val alt: Int,
    val count: Int,
    val createdAt: String,
    val desc: String,
    val desc_promo: String,
    val latitude: String,
    val lokasi: String,
    val longitude: String,
    val nama: String,
    val name_promo: String,
    val title: String,
    val updatedAt: String
) : Parcelable