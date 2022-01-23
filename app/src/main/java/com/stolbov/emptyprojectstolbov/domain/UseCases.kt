package com.stolbov.emptyprojectstolbov.domain

import com.stolbov.emptyprojectstolbov.data.Repository
import com.stolbov.emptyprojectstolbov.data.Song
import com.stolbov.emptyprojectstolbov.data.SongPreview

class UseCases {
    val repository = Repository()
    private var repositoryDataPreview = repository.getPreviewSongsData()
    private var repositoryDataFull = repository.getFullSongsData()

    private fun updateDataPreview(){
        repositoryDataPreview = Repository().getPreviewSongsData()
    }

    private fun updateDataFull(){
        repositoryDataFull = Repository().getFullSongsData()
    }

    fun getSongsMap(): Map<Int, SongPreview> {
        return repositoryDataPreview
    }

    fun getSongById(id: Int): Song? {
        return repositoryDataFull[id]
    }

}