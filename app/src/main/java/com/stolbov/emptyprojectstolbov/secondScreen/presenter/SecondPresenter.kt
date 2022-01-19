package com.stolbov.emptyprojectstolbov.secondScreen.presenter

import com.stolbov.emptyprojectstolbov.data.ItemsViewModel
import com.stolbov.emptyprojectstolbov.secondScreen.ISecondInterface
import kotlinx.coroutines.*


class SecondPresenter(private val view: ISecondInterface.SecondViewInterface): ISecondInterface.SecondPresenterInterface {
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun addCat(itemsViewModel: ItemsViewModel) {//itemsViewModel использвуется только в качестве хранилища
        runTask(2000, {view.showWaitState()}, {view.showNewCat()})
        itemsViewModel.countCatsSecondActivity++

    }

    override fun runTask(latency: Long, task: () -> Unit, onFinish: () -> Unit) {
        scope.launch {
            withContext(Dispatchers.Main) {task()}
            delay(latency)
            withContext(Dispatchers.Main) { onFinish() }
        }
    }
}