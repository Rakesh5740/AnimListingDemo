package com.animlistingdemo

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.Player.STATE_READY
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.animlistingdemo.data.AnimDetails
import com.animlistingdemo.databinding.AnimDetailsActivityBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AnimDetailsActivity : AppCompatActivity() {

    private var animeId: Int = 0
    private lateinit var binding: AnimDetailsActivityBinding

    private val viewModel: MainViewModel by viewModels()

    private var mediaUrl = ""

    private var player: ExoPlayer? = null

    //    // Create a data source factory.
    private val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AnimDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animeId = intent.extras?.getInt("animeId") ?: 0
        animeId.let {
            viewModel.fetchDetails(it)
        }
        setData()
    }

    private fun setData() {
        viewModel.detailsResponse.observe(this) {
            binding.progressbar.visibility = View.GONE

            it?.let {
                bindData(it)
            }
        }
    }

    private fun bindData(item: AnimDetails) {
        binding.title.text = item.title
        binding.rating.text = item.rating
        binding.plotSynopsis.text = item.synopsis
        binding.genre.text = item.genre
        binding.numEpisode.text = item.numberOfEpisode.toString()
        mediaUrl = item.videoUrl.toString()

        if (mediaUrl != "") {
            binding.posterImage.visibility = View.GONE
            binding.playerView.visibility = View.VISIBLE
        } else {
            binding.posterImage.visibility = View.VISIBLE
            Glide.with(this)
                .load(item.posterImage)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.posterImage)
        }

        initPlayer()
    }

    override fun onPause() {
        super.onPause()
        pause()
    }

    override fun onResume() {
        super.onResume()
        play()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    @OptIn(UnstableApi::class)
    private fun initPlayer() {
        player = ExoPlayer.Builder(this)
            .build()
            .apply {
                val source = if (mediaUrl.contains("m3u8"))
                    getHlsMediaSource()
                else {
                    getProgressiveMediaSource()
                }

                setMediaSource(source)
                prepare()
                addListener(playerListener)
            }
    }

    @OptIn(UnstableApi::class)
    private fun getHlsMediaSource(): MediaSource {
        // Create a HLS media source pointing to a playlist uri.
        DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true)
        return HlsMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(mediaUrl))
    }

    @OptIn(UnstableApi::class)
    private fun getProgressiveMediaSource(): MediaSource {
        DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true)
        // Create a Regular media source pointing to a playlist uri.
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(mediaUrl)))
    }

    private fun releasePlayer() {
        player?.apply {
            playWhenReady = false
            release()
        }
        player = null
    }

    private fun pause() {
        player?.playWhenReady = false
    }

    private fun play() {
        player?.playWhenReady = true
    }

    private fun restartPlayer() {
        player?.seekTo(0)
        player?.playWhenReady = true
    }

    private val playerListener = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            when (playbackState) {
                STATE_ENDED -> restartPlayer()
                STATE_READY -> {
                    binding.playerView.player = player
                    play()
                }
            }
        }
    }
}