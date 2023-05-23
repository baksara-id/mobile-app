package com.baksara.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.database.Modul
import com.baksara.app.databinding.ItemModuleBinding

class ListModulAdapter(private val moduls: List<Modul>): RecyclerView.Adapter<ListModulAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemModuleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(modul: Modul, position:Int){
            binding.tvItemJudul.text = "Modul " + modul.nomor
            binding.tvItemDetail.text = modul.deskripsi
            binding.tvItemLogo.text = modul.url_background
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemModuleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val modul = moduls[position]
        holder.bind(modul, position)
    }

    override fun getItemCount(): Int = moduls.size
}