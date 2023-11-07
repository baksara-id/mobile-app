package com.baksara.app.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.databinding.ItemArtikelBinding
import com.baksara.app.response.Artikel
import com.baksara.app.response.Tantangan
import com.baksara.app.ui.artikel.DetailArtikelActivity
import com.baksara.app.ui.pustaka.DetailCeritaActivity
import com.bumptech.glide.Glide

class ListArtikelAdapter(private var artikels: List<Artikel>): RecyclerView.Adapter<ListArtikelAdapter.ListViewHolder>()  {
    inner class ListViewHolder(private val binding: ItemArtikelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artikel: Artikel, position: Int) {
            binding.tvJudulArtikel.text = artikel.judul
            val isiLength = artikel.isi?.length ?: 0
            var artikeldescription = artikel.isi
            if(isiLength > 40) artikeldescription = artikel.isi?.substring(0,40) + " ... "
            binding.tvDeskripsiArtikel.text = artikeldescription

            Glide.with(this.itemView)
                .load(artikel.url_gambar)
                .placeholder(R.drawable.img_placeholder)
                .fitCenter()
                .into(binding.imageView);

            binding.cardViewArtikel.setOnClickListener {
                val intent = Intent(itemView.context, DetailArtikelActivity::class.java)
                intent.putExtra(DetailArtikelActivity.ARTIKELID, artikel.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListArtikelAdapter.ListViewHolder {
        val binding = ItemArtikelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListArtikelAdapter.ListViewHolder, position: Int) {
        val artikel = artikels[position]
        holder.bind(artikel, position)
    }

    override fun getItemCount(): Int = artikels.size

    @SuppressLint("NotifyDataSetChanged")
    fun setListArtikel(artikelBaru: List<Artikel>) {
        artikels = artikelBaru
        notifyDataSetChanged()
    }
}
