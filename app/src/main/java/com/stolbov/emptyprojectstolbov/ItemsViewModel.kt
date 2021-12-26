package com.stolbov.emptyprojectstolbov

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.stolbov.emptyprojectstolbov.adapter.MyAdapter
import com.stolbov.emptyprojectstolbov.data.MyItem
import kotlin.random.Random

class ItemsViewModel(application: Application): AndroidViewModel(application) {
    var items = mutableListOf<MyItem>()

    var countCatsSecondActivity = 1

    init {
        items = MutableList(MainActivity.INIT_LIST_SIZE) {
            when (Random.nextInt(1, MyAdapter.ViewType.values().size + 1)){
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