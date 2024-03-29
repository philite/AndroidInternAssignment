package com.poomon.androidinternassignment.data

import android.widget.ImageView

data class CoinCollection(
    val status: String,
    val data: Data
)

data class Data(
    val coins: MutableList<Coin>
)

data class Coin(
    val name: String,
    val description: String,
    val iconUrl: String,
    val iconImg: ImageView
)