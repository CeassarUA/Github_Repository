package com.kirik.repository.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//На екрані деталів репозиторія відобразити: назву, повний опис,
//кількість зірок, мову програмування, дату створення, кількість
//відкритих issues.
@Parcelize
data class Repository(
    val id: Int,
    val fullName: String,
    val name: String,
    val description: String?,
    val watchersCount: Int,
    val stars: Int,
    val image: String?,
    val language: String,
    val createdAt: String,
    val issues: Int
) : Parcelable
