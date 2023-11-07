package aji.dev.bniassessment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val attributes: Attributes,
    val id: Int
): Parcelable