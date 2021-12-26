package com.stolbov.emptyprojectstolbov.data

sealed class MyItem{

    data class FirstItem(
        val id: String,
        val textItem: String
    ): MyItem()

    object SecondItem : MyItem()

    object ThirdItem : MyItem()
}
