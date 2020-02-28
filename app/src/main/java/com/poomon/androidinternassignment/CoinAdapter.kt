package com.poomon.androidinternassignment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poomon.androidinternassignment.data.Coin
import com.poomon.androidinternassignment.databinding.ItemCoinBinding

class CoinAdapter() : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    private var binding : ItemCoinBinding? = null
    var data = mutableListOf<Coin>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val nameText: TextView = binding.nameText
//        val descriptionText: TextView = binding.descriptionText
    }

    fun updateData(newData: MutableList<Coin>){
        this.data = newData
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding!!.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding!!.nameText.text = data[position].name
        binding!!.descriptionText.text = data[position].description

        // Logging
        //Log.d("ViewHolderPosition", position.toString())
    }

    override fun getItemCount(): Int = data.size

}