package com.poomon.androidinternassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poomon.androidinternassignment.data.Coin
import com.poomon.androidinternassignment.data.CoinCollection
import com.poomon.androidinternassignment.service.CoinApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class CoinViewModel: ViewModel() {

    // LiveData
    private val _response = MutableLiveData<MutableList<Coin>>()
    val data: LiveData<MutableList<Coin>>
        get() = _response

    init {
        fetchCoins()
    }

    fun fetchCoins(){
        CoinApi.retrofitService.getWithLimit(30).enqueue(
            object : Callback<CoinCollection> {
                override fun onFailure(call: Call<CoinCollection>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<CoinCollection>, response: Response<CoinCollection>) {
                    _response.value = response.body()?.data?.coins
                }
            }
        )
    }
}