package com.animlistingdemo.repository.mapper

import com.animlistingdemo.data.AnimDetails
import com.animlistingdemo.data.AnimDetailsResponse
import com.animlistingdemo.data.AnimItem
import com.animlistingdemo.data.MyResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AnimListMapper {

    suspend fun mapData(
        myResponse: MyResponse,
        dispatcher: CoroutineDispatcher = Dispatchers.Default
    ): List<AnimItem> = withContext(dispatcher) {

        val list = myResponse.data.map {
            AnimItem(
                animeId = it.mal_id,
                title = it.title,
                numberOfEpisode = it.episodes,
                rating = it.rating,
                posterImage = it.images.jpg.image_url
            )
        }
        return@withContext list
    }

    suspend fun mapAnimDetails(
        animDetailsResponse: AnimDetailsResponse,
        dispatcher: CoroutineDispatcher = Dispatchers.Default
    ): AnimDetails = withContext(dispatcher) {

        val details = animDetailsResponse.data.let { data ->
            AnimDetails(
                animeId = data.mal_id,
                title = data.title,
                numberOfEpisode = data.episodes,
                rating = data.rating,
                synopsis = data.synopsis,
                posterImage = data.images.jpg.image_url,
                videoUrl = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8",
                genre = data.genres[0].name
            )
        }
        return@withContext details
    }

}