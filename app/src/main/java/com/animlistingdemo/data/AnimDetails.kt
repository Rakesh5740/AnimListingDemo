package com.animlistingdemo.data

data class AnimDetails(
    var animeId: Int? = null,
    var title: String? = null,
    var posterImage: String? = null,
    var numberOfEpisode: Int? = null,
    var synopsis: String? = null,
    var rating: String? = null,
    var videoUrl: String? = null,
    var genre: String? = null
)
