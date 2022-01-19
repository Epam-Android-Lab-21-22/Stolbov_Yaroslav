package com.stolbov.emptyprojectstolbov.secondScreen

import com.stolbov.emptyprojectstolbov.data.ItemsViewModel

interface ISecondInterface {
    interface SecondViewInterface{
        fun showNewCat()
        fun showWaitState()
    }

    interface SecondPresenterInterface{
        fun addCat(itemsViewModel: ItemsViewModel)
        fun runTask(latency: Long, task: () -> Unit, onFinish: () -> Unit)
    }
}