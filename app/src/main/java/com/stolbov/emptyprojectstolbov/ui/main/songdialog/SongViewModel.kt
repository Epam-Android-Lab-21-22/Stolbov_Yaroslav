package com.stolbov.emptyprojectstolbov.ui.main.songdialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stolbov.emptyprojectstolbov.data.Song
import com.stolbov.emptyprojectstolbov.domain.UseCases

class SongViewModel : ViewModel() {
    private var id: Int = 0
    private val _song: MutableLiveData<Song> = MutableLiveData()
    val song: LiveData<Song> = _song

    fun updateSongData(){
        _song.value = UseCases().getSongById(id + 1)
    }

    fun setIdSong(songId: Int){
        id = songId
    }
}