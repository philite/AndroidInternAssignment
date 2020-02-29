package com.poomon.androidinternassignment.model

import android.util.Log
import androidx.paging.DataSource

class CoinDataFactory: DataSource.Factory<Int, Coin>(){
    override fun create(): DataSource<Int, Coin> {
        Log.d("DataSource:Factory", "")
        return CoinDataSource()
    }

}