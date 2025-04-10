package com.animlistingdemo.data

data class DataX(
    val episodes: Int,
    val explicit_genres: List<Any?>,
    val genres: List<GenreX>,
    val images: ImagesX,
    val mal_id: Int,
    val rating: String,
    val source: String,
    val synopsis: String,
    val title: String,
    val titles: List<Title>,
    val trailer: TrailerX,
)