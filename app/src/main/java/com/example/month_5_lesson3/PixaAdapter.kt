package com.example.month_5_lesson3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.month_5_lesson3.databinding.ItemImgBinding

class PixaAdapter(private val list: ArrayList<Hit>) :
    RecyclerView.Adapter<PixaAdapter.PixaViewHolder>() {

    fun addData(list: List<Hit>) {
        this.list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemImgBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class PixaViewHolder(private val binding: ItemImgBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(hit: Hit) {
            binding.imageView.load(hit.largeImageURL)
        }
    }
}