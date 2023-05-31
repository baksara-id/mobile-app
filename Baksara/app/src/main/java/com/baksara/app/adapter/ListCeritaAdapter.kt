package com.baksara.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.database.Cerita
import com.baksara.app.databinding.ItemCeritaBinding
import com.baksara.app.ui.DetailCeritaActivity
import com.bumptech.glide.Glide

class ListCeritaAdapter(private val ceritas: List<Cerita>): RecyclerView.Adapter<ListCeritaAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemCeritaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cerita: Cerita, position: Int) {
            binding.tvJudul.text = cerita.judul
            binding.tvDeskripsi.text = cerita.deskripsi

            Glide.with(this.itemView)
                .load(cerita.url_img)
                .placeholder(R.drawable.arjunadummy2)
                .fitCenter()
                .into(binding.imageView);

            binding.cardViewCerita.setOnClickListener {
                val intent = Intent(itemView.context, DetailCeritaActivity::class.java)
                intent.putExtra(DetailCeritaActivity.CERITA_ID, cerita.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCeritaAdapter.ListViewHolder {
        val binding = ItemCeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListCeritaAdapter.ListViewHolder, position: Int) {
        val cerita = ceritas[position]
        holder.bind(cerita, position)
    }

    override fun getItemCount(): Int = ceritas.size
}
