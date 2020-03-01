package com.poomon.androidinternassignment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.poomon.androidinternassignment.model.Coin
import com.poomon.androidinternassignment.databinding.ItemCoinBinding

class CoinAdapter : PagedListAdapter<Coin, CoinAdapter.ViewHolder>(CoinAdapterDiffCallback()) {

//    var data = mutableListOf<Coin>()

    class ViewHolder(val binding: ItemCoinBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCoinBinding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.nameText.text = item?.name
        holder.binding.descriptionText.text = item?.description

        // Logging
        Log.d("LiveData ViewHolder", "Item at " + position.toString() + " Name = " + getItem(position)?.name)
    }

}

class CoinAdapterDiffCallback: DiffUtil.ItemCallback<Coin>(){
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.name == oldItem.name
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }
}