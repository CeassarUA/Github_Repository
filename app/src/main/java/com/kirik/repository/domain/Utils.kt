package com.kirik.repository.domain

fun String.splitToParts(): Pair<String, String> {
    val list = split('/')
    return (list.firstOrNull() ?: "") to (list.lastOrNull() ?: "")
}