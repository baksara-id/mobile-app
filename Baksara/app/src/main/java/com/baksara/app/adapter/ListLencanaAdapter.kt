package com.baksara.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.database.Lencana
import com.baksara.app.databinding.ItemLencanaBinding
import com.bumptech.glide.Glide

class ListLencanaAdapter(private val lencanas: List<Lencana>): RecyclerView.Adapter<ListLencanaAdapter.ListViewHolder>(){
    inner class ListViewHolder(private val binding: ItemLencanaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(lencana: Lencana, position: Int) {
            Glide.with(itemView.context).load(lencana.url_img)
                .centerCrop()
                .into(binding.imgLencana)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemLencanaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val lencana = lencanas[position]
        holder.bind(lencana, position)
    }

    override fun getItemCount(): Int = lencanas.size
}