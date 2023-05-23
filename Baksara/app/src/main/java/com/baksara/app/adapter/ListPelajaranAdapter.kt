package com.baksara.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.database.Pelajaran
import com.baksara.app.databinding.ItemKelasBinding

class ListPelajaranAdapter(private val pelajarans: List<Pelajaran>): RecyclerView.Adapter<ListPelajaranAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemKelasBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pelajaran: Pelajaran, position:Int){
            binding.tvJudul.text = "Pelajaran " + pelajaran.nomor
            binding.tvAksaraPelajaran.text = pelajaran.deskripsiAksara
            binding.tvDeskripsi.text = pelajaran.deskripsiLatin

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemKelasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val pelajaran = pelajarans[position]
        holder.bind(pelajaran, position)
    }

    override fun getItemCount(): Int = pelajarans.size
}