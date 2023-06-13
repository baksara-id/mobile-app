package com.baksara.app.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.databinding.ItemCeritaBinding
import com.baksara.app.response.Cerita
import com.baksara.app.response.Tantangan
import com.baksara.app.ui.pustaka.DetailCeritaActivity
import com.bumptech.glide.Glide

class ListCeritaAdapter(private var ceritas: List<Cerita>): RecyclerView.Adapter<ListCeritaAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemCeritaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cerita: Cerita, position: Int) {
            binding.tvJudul.text = cerita.judul
            val deskripsiLength = cerita.deskripsi?.length ?: 0
            var deskripsi = cerita.deskripsi
            if(deskripsiLength > 40) deskripsi = cerita.deskripsi?.substring(0,40) + " ... "
            binding.tvDeskripsi.text = deskripsi

            Glide.with(this.itemView)
                .load(cerita.url_gambar)
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

    @SuppressLint("NotifyDataSetChanged")
    fun setListCerita(ceritaBaru: List<Cerita>) {
        ceritas = ceritaBaru
        notifyDataSetChanged()
    }
}
