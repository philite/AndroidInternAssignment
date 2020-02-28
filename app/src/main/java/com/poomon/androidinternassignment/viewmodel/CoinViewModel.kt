package com.poomon.androidinternassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poomon.androidinternassignment.data.Coin

class CoinViewModel: ViewModel() {
    // Recycler Adapter
    // Coin Data
    val data = mutableListOf<Coin>()

    // LiveData
//    private val _data = MutableLiveData<Coin>()
//    val data: LiveData<Coin>
//        get() =_data


    init {
        // Dummy data
        for(x in 0..15) {
            data.add(Coin("BTC", "Lorem ipsum dolor sit amet, per no facilisi conceptam adversarium.\n" +
                    "        Ipsum semper comprehensam usu no. No agam scribentur omittantur vim, no has nibh mucius invidunt,\n" +
                    "        ad pri sint interpretaris. Est in magna utroque, novum deleniti constituam mel at. Est repudiare tincidunt no,\n" +
                    "        dicat ponderum erroribus cu pro."
                , "google.com"))
        }
    }
}