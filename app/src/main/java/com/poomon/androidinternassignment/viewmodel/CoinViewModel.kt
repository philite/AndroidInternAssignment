package com.poomon.androidinternassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.poomon.androidinternassignment.data.Coin
import com.poomon.androidinternassignment.data.CoinDataSourceFactory
import com.poomon.androidinternassignment.data.CoinPositionalDataSource

class CoinViewModel: ViewModel() {

    // PagedList
    private var pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(false)
        .setPrefetchDistance(10)
        .setInitialLoadSizeHint(10)
        .setPageSize(10)
        .build()
    private val sourceFactory = CoinDataSourceFactory()

    // LiveData
    lateinit var response: LiveData<PagedList<Coin>>
    lateinit var liveDataSource: LiveData<CoinPositionalDataSource>
    lateinit var networkState: LiveData<String>

    init {
        initial()
    }

    private fun initial(){
        response = LivePagedListBuilder(sourceFactory, pagedListConfig).build()
        liveDataSource = sourceFactory.mutableLiveData
        // Every time liveDataSource value changed --> networkState = DataSource.networkState
        networkState = Transformations.switchMap(liveDataSource, CoinPositionalDataSource::networkState)
    }
}