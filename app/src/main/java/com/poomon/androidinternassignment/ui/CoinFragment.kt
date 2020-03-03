package com.poomon.androidinternassignment.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.poomon.androidinternassignment.R
import com.poomon.androidinternassignment.adapter.CoinPagedListAdapter
import com.poomon.androidinternassignment.data.Coin
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
        subscribeUi(viewModel.response, viewModel.networkState)

        return binding.root
    }


    private fun initView(){
        val divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider_custom)

        layout = LinearLayoutManager(context)
        coinAdapter = CoinPagedListAdapter()
        dividerItemDecoration = DividerItemDecoration(context, layout.orientation)
        dividerItemDecoration.setDrawable(divider!!)

        binding.coinRecycler.apply{
            layoutManager = layout
            adapter = coinAdapter
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun subscribeUi(liveData: LiveData<PagedList<Coin>>, networkState: LiveData<String>){
        liveData.observe(viewLifecycleOwner, Observer {newData->
            if (newData != null){
                coinAdapter.submitList(newData)
                // Logging
                Log.d("LiveData Fragment", "LiveData updated")
            }
        })
        networkState.observe(viewLifecycleOwner, Observer { newState ->
            showToast(newState)
        })
    }

    private fun showToast(message: String){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}