package com.kirik.repository.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Repository(
    val id: Int,
    val fullName: String,
    val name: String,
    val description: String?,
    val watchersCount: Int,
    val stars: Int,
    val image: String?
):Parcelable
