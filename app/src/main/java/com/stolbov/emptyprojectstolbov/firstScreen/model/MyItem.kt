package com.stolbov.emptyprojectstolbov.firstScreen.model

sealed class MyItem{

    data class FirstItem(
        val id: String,
        val textItem: String
    ): MyItem()

    object SecondItem : MyItem()

    object ThirdItem : MyItem()
}
