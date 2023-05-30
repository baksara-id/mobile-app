package com.baksara.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.database.KamusBelajar
import com.baksara.app.databinding.ItemKamusBinding
import com.baksara.app.ui.DetailKamusActivity

class ListKamusAdapter(private val kamus: List<KamusBelajar>): RecyclerView.Adapter<ListKamusAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemKamusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kamus: KamusBelajar, position: Int) {
            binding.tvItemJudul.text = kamus.judul
            binding.tvItemDetail.text = kamus.jumlah
            binding.tvItemLogo.text = kamus.aksara

            binding.cardViewKamusAksara.setOnClickListener {
                val intent = Intent(itemView.context, DetailKamusActivity::class.java)
                intent.putExtra(DetailKamusActivity.KAMUSID, kamus.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKamusAdapter.ListViewHolder {
        val binding = ItemKamusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListKamusAdapter.ListViewHolder, position: Int) {
        val materiKamus = kamus[position]
        holder.bind(materiKamus, position)
    }

    override fun getItemCount(): Int = kamus.size
}