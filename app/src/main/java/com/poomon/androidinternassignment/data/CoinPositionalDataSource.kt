package com.poomon.androidinternassignment.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.poomon.androidinternassignment.service.CoinApi
import kotlinx.coroutines.*

class CoinPositionalDataSource: PositionalDataSource<Coin>() {

    // LiveData
    private var data = mutableListOf<Coin>()
    private val _networkState = MutableLiveData<String>()
    val networkState: LiveData<String>
    get() = _networkState

    // ApiService
    private val api = CoinApi.retrofitService

    // Error message
    private val errorMessage = "Loading failed, please check your internet connection"

    // Initial
    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Coin>) {

        GlobalScope.launch(Dispatchers.Main) {
            try{

                val apiResponse = withContext(Dispatchers.IO) {
                    api.getRankWithOffsetLimit(params.requestedStartPosition, params.requestedLoadSize)
                }

                data = apiResponse.data.coins
                callback.onResult(data, 0)
                Log.d("LiveData", "Status = " + apiResponse.status)

//                Log.d("LiveData", "loadInitial" + params.requestedStartPosition.toString() + " "
//                        + params.requestedLoadSize.toString())
            } catch (e: Exception){
                Log.d("LiveData", "loadInitialFailed: $e")
                _networkState.postValue(errorMessage)
            }
        }
    }

    // Later on
    override fun loadRange(params: LoadRangeParams,
                           callback: LoadRangeCallback<Coin>) {

        // Use Dispatchers.Main to capture error
        GlobalScope.launch(Dispatchers.Main) {
            try{

                val apiResponse = withContext(Dispatchers.IO) {
                    api.getRankWithOffsetLimit(params.startPosition, params.loadSize)
                }

                data = apiResponse.data.coins
                callback.onResult(data)

//                Log.d("LiveData", "loadRange " + params.startPosition.toString() + " " +
//                        params.loadSize.toString())
                Log.d("LiveData", "Status = " + apiResponse.status)

            } catch (e: Exception){
                Log.d("LiveData", "loadRangeFailed: $e")
                _networkState.postValue(errorMessage)
            }
        }
    }
}