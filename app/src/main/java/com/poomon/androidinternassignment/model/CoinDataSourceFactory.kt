package com.poomon.androidinternassignment.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class CoinDataSourceFactory: DataSource.Factory<Int, Coin>(){

    private val _mutableLiveData = MutableLiveData<CoinPositionalDataSource>()
    val mutableLiveData: LiveData<CoinPositionalDataSource>
    get() = _mutableLiveData

    override fun create(): DataSource<Int, Coin> {
        val coinData =  CoinPositionalDataSource()
        _mutableLiveData.postValue(coinData)
        return coinData
    }



}