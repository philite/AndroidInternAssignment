package com.poomon.androidinternassignment.model

import android.util.Log
import androidx.paging.PositionalDataSource
import com.poomon.androidinternassignment.service.CoinApi
import kotlinx.coroutines.*

class CoinDataSource: PositionalDataSource<Coin>() {

    private var apiJob = Job()
    private val coroutineScope = CoroutineScope(
        apiJob + Dispatchers.IO)

    private var data = mutableListOf<Coin>()

    override fun loadRange(params: LoadRangeParams,
                           callback: LoadRangeCallback<Coin>) {

        Log.d("LiveData", "loadRange " + params.startPosition.toString() + " " +
                params.loadSize.toString())

        coroutineScope.launch {
            val apiResponse = CoinApi.retrofitService.
                getRankWithOffsetLimit(params.startPosition, params.loadSize)

            try {
                data = apiResponse.data.coins
                Log.d("LiveData", data[0].name)
                Log.d("LiveData", data.toString())
                callback.onResult(data)
            } catch (e: Exception){
                //TODO
            }
        }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Coin>) {

        Log.d("LiveData", "loadInitial" + params.requestedStartPosition.toString() + " "
        + params.requestedLoadSize.toString())

        coroutineScope.launch {
            val apiResponse = CoinApi.retrofitService.
                getRankWithOffsetLimit(params.requestedStartPosition, params.requestedLoadSize)

            try {
                data = apiResponse.data.coins
                callback.onResult(data, 0)
            } catch (e: Exception){
                //TODO
            }
        }
    }
}