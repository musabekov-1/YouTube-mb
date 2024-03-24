package com.example.youtube.ui.playlists

import android.os.Bundle
import android.view.View
import com.example.youtube.data.base.BaseActivity
import com.example.youtube.databinding.ActivityPlaylistBinding
import com.example.youtube.ui.playlists.adapter.PlaylistDetailAdapter
import com.example.youtube.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistActivity : BaseActivity() {
    private lateinit var binding: ActivityPlaylistBinding
    private val viewModel: DetailViewModel by viewModel ()
    private val adapter by lazy {
        PlaylistDetailAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvPlaylistItems.adapter = adapter

        val getId = intent.getStringExtra("id").toString()
        val getTitle = intent.getStringExtra("title").toString()
        val getDesc = intent.getStringExtra("description").toString()
        val getSize = intent.getIntExtra("size", 0)

        binding.tvTitle.text = getTitle
        binding.tvDescription.text = getDesc

        viewModel.getPlaylistVideo(getId, getSize).stateHandler(
            success = {
                adapter.submitList(it)
            },
            state = { binding.progressBar.visibility = if (it is Resource.Loading) View.VISIBLE else View.GONE }        )
    }
}