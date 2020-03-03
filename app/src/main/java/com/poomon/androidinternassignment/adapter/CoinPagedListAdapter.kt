package com.poomon.androidinternassignment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import com.poomon.androidinternassignment.R
import com.poomon.androidinternassignment.data.Coin
import com.poomon.androidinternassignment.databinding.ItemCoinBinding

private const val nullDescription = "No description provided."

class CoinPagedListAdapter : PagedListAdapter<Coin, CoinPagedListAdapter.ViewHolder>(CoinAdapterDiffCallback()) {

    class ViewHolder(val binding: ItemCoinBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCoinBinding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.nameText.text = item?.name

        if (item?.description == null){
            holder.binding.descriptionText.text = nullDescription
        }
        else {
            holder.binding.descriptionText.text = item.description
        }

        // Check if not null -> Load icon
        item?.iconUrl.let {
            // Required kotlinOptions { jvmTarget = "1.8" }
            val iconUri = item?.iconUrl
                ?.toUri()?.buildUpon()?.scheme("https")?.build()

            GlideToVectorYou
                .init()
                .with(holder.binding.coinImage.context)
                .withListener(object: GlideToVectorYouListener {
                    override fun onLoadFailed() {
                        Log.d("ImageData", "ImageFailed: " + item!!.name)
                    }

                    override fun onResourceReady() {
                        Log.d("ImageData", "ImageLoaded: " + item!!.name)
                    }
                })
                .setPlaceHolder(R.drawable.loading_animation, R.drawable.ic_broken_image)
                .load(iconUri, holder.binding.coinImage)
        }

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