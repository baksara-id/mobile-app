package com.baksara.app.adapter

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.database.Penggunaan
import com.baksara.app.databinding.ItemPenggunaanBinding


class ListKamusContohAdapter(private val penggunaan: List<Penggunaan>): RecyclerView.Adapter<ListKamusContohAdapter.ListViewHolder>(){
    inner class ListViewHolder(private val binding: ItemPenggunaanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(penggunaan: Penggunaan, position: Int) {
            val textInHTMLFormat = penggunaan.aksara
            binding.tvContohAksara.text = Html.fromHtml(textInHTMLFormat, Html.FROM_HTML_MODE_LEGACY)
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