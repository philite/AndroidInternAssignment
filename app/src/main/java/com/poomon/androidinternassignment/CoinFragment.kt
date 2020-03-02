package com.poomon.androidinternassignment

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.poomon.androidinternassignment.adapter.CoinPagedListAdapter
import com.poomon.androidinternassignment.model.Coin
import com.poomon.androidinternassignment.databinding.FragmentCoinBinding
import com.poomon.androidinternassignment.viewmodel.CoinViewModel

class CoinFragment: Fragment() {

    // View Binding
    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!! // Backing property

    // Factory for ViewModel
    private val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
    private lateinit var viewModel: CoinViewModel

    // Recycler
    private lateinit var layout: LinearLayoutManager
    private lateinit var coinAdapter: CoinPagedListAdapter
    private lateinit var dividerItemDecoration: DividerItemDecoration

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        _binding = FragmentCoinBinding.inflate(inflater, container, false)

        viewModel = factory.create(CoinViewModel::class.java)
        initView()
        subscribeUi(viewModel.response)

        return binding.root
    }


    private fun initView(){
        layout = LinearLayoutManager(context)
        coinAdapter = CoinPagedListAdapter()
        dividerItemDecoration = DividerItemDecoration(context, layout.orientation)

        binding.coinRecycler.apply{
            layoutManager = layout
            adapter = coinAdapter
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun subscribeUi(liveData: LiveData<PagedList<Coin>>){
        liveData.observe(viewLifecycleOwner, Observer {newData->
            if (newData != null){
                coinAdapter.submitList(newData)
                // Logging
                Log.d("LiveData Fragment", "LiveData updated")
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}