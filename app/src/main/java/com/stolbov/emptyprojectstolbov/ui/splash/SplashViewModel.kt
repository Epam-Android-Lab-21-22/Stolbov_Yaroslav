package com.stolbov.emptyprojectstolbov.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class SplashViewModel: ViewModel() {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val _currentProgress: MutableLiveData<Int> = MutableLiveData(0)
    val currentProgress: LiveData<Int> = _currentProgress

    fun imitationOfLoad(){
        scope.launch {
            withContext(Dispatchers.Main){
                for (num in 1..10){
                    delay(200)
                    _currentProgress.value = num * 10
                }
            }
        }
    }
}