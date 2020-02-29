package com.poomon.androidinternassignment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poomon.androidinternassignment.data.Coin
import com.poomon.androidinternassignment.databinding.ItemCoinBinding

class CoinAdapter() : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    var data = mutableListOf<Coin>()

    class ViewHolder(val binding: ItemCoinBinding): RecyclerView.ViewHolder(binding.root)

    fun updateData(newData: MutableList<Coin>){
        data = newData
        notifyDataSetChanged()
        Log.d("LiveData Adapter", "Data changed to " + data[0].name + " size = " + data.size.toString())
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCoinBinding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.nameText.text = data[position].name
        holder.binding.descriptionText.text = data[position].description

        // Logging
        Log.d("LiveData ViewHolder", "Item at " + position.toString() + " Name = " + data[position].name)
    }

    override fun getItemCount(): Int = data.size

}