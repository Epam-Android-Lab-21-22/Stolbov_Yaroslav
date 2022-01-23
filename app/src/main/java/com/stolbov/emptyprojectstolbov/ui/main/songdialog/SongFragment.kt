package com.stolbov.emptyprojectstolbov.ui.main.songdialog

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.stolbov.emptyprojectstolbov.R
import com.stolbov.emptyprojectstolbov.databinding.FragmentSongBinding
import com.stolbov.emptyprojectstolbov.ui.main.recycler.MainAdapter.Companion.DIALOG_KEY
import com.stolbov.emptyprojectstolbov.ui.main.recycler.RecyclerViewModel

class SongFragment : DialogFragment() {
    private var _binding: FragmentSongBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SongViewModel by lazy{
        ViewModelProvider(this).get(SongViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSongBinding.inflate(inflater, container, false)
        val view = binding.root

        initViewModel()

        displayViews()

        return view
    }

    private fun displayViews() {
        val song = viewModel.song.value!!
        binding.songTittle.text = song.title
        binding.songYear.text = song.year
        binding.songAlbum.text = song.album
        binding.songGenre.text = song.genre
        binding.songPerformer.text = song.performer
        binding.songText.text = song.text
        binding.songText.movementMethod = ScrollingMovementMethod()
        Picasso.get().load(song.coverUrl)
            .placeholder(R.drawable.cover_default)
            .error(R.drawable.cover_default)
            .into(binding.songImage)
    }

    private fun initViewModel() {
        viewModel.setIdSong(arguments?.getInt(DIALOG_KEY)!!)
        viewModel.updateSongData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}