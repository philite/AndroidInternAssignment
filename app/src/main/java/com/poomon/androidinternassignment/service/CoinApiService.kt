package com.poomon.androidinternassignment.service

import androidx.annotation.IntRange
import com.poomon.androidinternassignment.data.CoinCollection
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.coinranking.com"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface CoinApiService {

    @GET("v1/public/coins")
    fun getWithLimit(
        @IntRange(from = 1, to=100)
        @Query("limit")
        limit: Int) : Call<CoinCollection>

}

object CoinApi {
    val retrofitService: CoinApiService by lazy {
        retrofit.create(CoinApiService::class.java)
    }
}