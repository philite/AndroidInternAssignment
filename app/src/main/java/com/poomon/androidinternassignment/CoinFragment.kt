package com.poomon.androidinternassignment

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.poomon.androidinternassignment.databinding.FragmentCoinBinding
import com.poomon.androidinternassignment.viewmodel.CoinViewModel

class CoinFragment: Fragment() {

    // View Binding
    private var _binding: FragmentCoinBinding? = null
    // Backing property
    private val binding get() = _binding!!

    // Factory for ViewModel
    private val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(Application())

    private lateinit var viewModel: CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        _binding = FragmentCoinBinding.inflate(inflater, container, false)
        viewModel = factory.create(CoinViewModel::class.java)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}