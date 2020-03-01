package com.poomon.androidinternassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.poomon.androidinternassignment.model.Coin
import com.poomon.androidinternassignment.model.CoinDataFactory
import kotlinx.coroutines.*

class CoinViewModel: ViewModel() {

    // LiveData
    lateinit var response: LiveData<PagedList<Coin>>

    // PagedList
    private var pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(false)
        .setPrefetchDistance(10)
        .setInitialLoadSizeHint(5)
        .setPageSize(5)
        .build()
    private val sourceFactory = CoinDataFactory()

    init {
        initial()
    }

    private fun initial(){
        response = LivePagedListBuilder(sourceFactory, pagedListConfig).build()
    }
}