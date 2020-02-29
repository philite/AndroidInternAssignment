package com.poomon.androidinternassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poomon.androidinternassignment.data.Coin
import com.poomon.androidinternassignment.service.CoinApi
import kotlinx.coroutines.*

class CoinViewModel: ViewModel() {

    // Coroutines
    private var viewModelJob = Job()
    // Dispatchers.Main = uiScope; Use uiScope because after ended, ui got updated (RecyclerView)
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    // LiveData
    private val _response = MutableLiveData<MutableList<Coin>>()
    val data: LiveData<MutableList<Coin>>
        get() = _response

    init {
        fetchCoins()
    }

    fun fetchCoins(){
        coroutineScope.launch {
            var api = CoinApi.retrofitService
            try {
                _response.value = withContext(Dispatchers.IO){
                    api.getWithLimit(30).data.coins
                }
            } catch (e: Exception){
                //TODO
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}