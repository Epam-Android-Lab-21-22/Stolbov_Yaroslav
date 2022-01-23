package com.stolbov.emptyprojectstolbov.data

data class Song(
    val id: String,
    val title: String,
    val performer: String,
    val coverUrl: String?,
    val year: String,
    val album: String,
    val genre: String,
    val text: String
    )