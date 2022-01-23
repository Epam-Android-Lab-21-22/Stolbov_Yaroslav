package com.stolbov.emptyprojectstolbov.ui.main.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stolbov.emptyprojectstolbov.data.SongPreview
import com.stolbov.emptyprojectstolbov.domain.UseCases

class RecyclerViewModel: ViewModel() {
    private val _songsMap: MutableLiveData<Map<Int, SongPreview>> = MutableLiveData()
    val songsMap: LiveData<Map<Int, SongPreview>> = _songsMap

    fun updateData(){
        _songsMap.value = UseCases().getSongsMap()
    }
}