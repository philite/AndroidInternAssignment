package com.poomon.androidinternassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poomon.androidinternassignment.data.Coin
import kotlin.random.Random

class CoinViewModel: ViewModel() {

    // LiveData
    private val _data = MutableLiveData<MutableList<Coin>>()
    val data: LiveData<MutableList<Coin>>
        get() =_data

    private val list1 = mutableListOf<Coin>()
    private val list2 = mutableListOf<Coin>()
    private val list3 = mutableListOf<Coin>()
    private val listAll = mutableListOf(list1, list2, list3)

    init {
        prepareData()
        fetchCoins()
    }

    private fun prepareData(){
        // Dummy data
        for(x in 0..15) {
            list1.add(Coin("${x}BTC", "Lorem ipsum dolor sit amet, per no facilisi conceptam adversarium." +
                    "        Ipsum semper comprehensam usu no. No agam scribentur omittantur vim, no has nibh mucius invidunt," +
                    "        ad pri sint interpretaris. Est in magna utroque, novum deleniti constituam mel at. Est repudiare tincidunt no," +
                    "        dicat ponderum erroribus cu pro."
                , "google.com"))
            Log.d("LiveData List1", list1[x].name)
        }
        for(x in 0..20) {
            list2.add(Coin("${x}ETH", "Do not go gentle into that good night, " +
                    "Old age should burn and rave at close of day; " +
                    "Rage, rage against the dying of the light. " +
                    "Though wise men at their end know dark is right, " +
                    "Because their words had forked no lightning they " +
                    "Do not go gentle into that good night. "
                , "google.com"))
            Log.d("LiveData List2", list2[x].name)
        }

        for(x in 0..10) {
            list3.add(Coin("${x}LIZ", "Demons run when a good man goes to war " +
                    "Night will fall and drown the sun " +
                    "When a good man goes to war " +
                    "Friendship dies and true love lies " +
                    "Night will fall and the dark will rise " +
                    "When a good man goes to war "
                , "google.com"))
            Log.d("LiveData List3", list3[x].name)
        }
    }

    private fun randomIndex() = Random.nextInt(from = 0, until = 2)

    fun fetchCoins(){
        // Pick randoms list
        _data.value = listAll[randomIndex()]
        Log.d("LiveData fetchCoins", "Called")
        Log.d("LiveData ViewModel" , _data.value!!.size.toString())
    }
}