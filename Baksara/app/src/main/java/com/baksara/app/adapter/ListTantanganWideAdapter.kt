package com.baksara.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.database.Tantangan
import com.baksara.app.databinding.ItemTantanganWideBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop

class ListTantanganWideAdapter(private val tantangans: List<Tantangan>): RecyclerView.Adapter<ListTantanganWideAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemTantanganWideBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tantangan: Tantangan, position:Int){
            binding.tvXpTantangan.text =tantangan.nama

            val jumlahXp = tantangan.exp.toString()
            binding.tvJudulTantangan.text = "$jumlahXp XP"
            Glide.with(itemView.context).load(tantangan.url_img)
                .centerCrop()
                .into(binding.imgTantangan)

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
}