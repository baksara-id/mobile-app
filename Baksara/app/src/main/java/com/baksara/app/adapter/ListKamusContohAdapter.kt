package com.baksara.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.database.Penggunaan
import com.baksara.app.databinding.ItemPenggunaanBinding


class ListKamusContohAdapter(private val penggunaan: List<Penggunaan>): RecyclerView.Adapter<ListKamusContohAdapter.ListViewHolder>(){
    inner class ListViewHolder(private val binding: ItemPenggunaanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(penggunaan: Penggunaan, position: Int) {
            binding.tvContohAksara.text = penggunaan.aksara
            binding.tvContohLatin.text = penggunaan.latin
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKamusContohAdapter.ListViewHolder {
        val binding = ItemPenggunaanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListKamusContohAdapter.ListViewHolder, position: Int) {
        val penggunaan = penggunaan[position]
        holder.bind(penggunaan, position)
    }

    override fun getItemCount(): Int = penggunaan.size
}