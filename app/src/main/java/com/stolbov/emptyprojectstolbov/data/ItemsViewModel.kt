package com.stolbov.emptyprojectstolbov.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.stolbov.emptyprojectstolbov.R
import com.stolbov.emptyprojectstolbov.firstScreen.view.FirstAdapter
import com.stolbov.emptyprojectstolbov.firstScreen.model.MyItem
import com.stolbov.emptyprojectstolbov.firstScreen.view.FirstActivity
import kotlin.random.Random

class ItemsViewModel(application: Application): AndroidViewModel(application) {
    var items = mutableListOf<MyItem>()

    var countCatsSecondActivity = 1

    init {
        items = MutableList(FirstActivity.INIT_LIST_SIZE) {
            when (Random.nextInt(1, FirstAdapter.ViewType.values().size + 1)){
                1 -> MyItem.FirstItem(
                    id = getApplication<Application>().resources.getString(R.string.id_text).plus(it),
                    textItem = getApplication<Application>().resources.getString(R.string.content_string)
                )
                2 -> MyItem.SecondItem
                else -> MyItem.ThirdItem
            }

        }
    }


}