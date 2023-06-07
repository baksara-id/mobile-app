package com.baksara.app.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.databinding.ItemTantanganWideBinding
import com.baksara.app.response.Tantangan
import com.baksara.app.ui.tantangan.SoalTantanganActivity
import com.bumptech.glide.Glide

class ListTantanganWideAdapter(var tantangans: List<Tantangan>): RecyclerView.Adapter<ListTantanganWideAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemTantanganWideBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tantangan: Tantangan, position:Int){
            val jumlahXp = tantangan.exp.toString()
            binding.tvXpTantangan.text = "$jumlahXp XP"

            binding.tvJudulTantangan.text = tantangan.nama
            Glide.with(itemView.context).load(tantangan.url_gambar)
                .centerCrop()
                .into(binding.imgTantangan)
            binding.root.setOnClickListener {
                val intent = Intent(itemView.context, SoalTantanganActivity::class.java)
                intent.putExtra(SoalTantanganActivity.TANTANGAN_ID, tantangan.id)
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemTantanganWideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tantangan = tantangans[position]
        holder.bind(tantangan, position)
    }

    override fun getItemCount(): Int = tantangans.size

    @SuppressLint("NotifyDataSetChanged")
    fun setListTantangan(newTantangans: List<Tantangan>) {
        tantangans = newTantangans
        notifyDataSetChanged()
    }
}