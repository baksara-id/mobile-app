package com.baksara.app.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.databinding.ItemTantanganRiwayatBinding
import com.baksara.app.response.Tantangan
import com.baksara.app.ui.tantangan.SoalTantanganActivity
import com.bumptech.glide.Glide

class ListTantanganWideRiwayatAdapter(var tantangans: List<Tantangan>): RecyclerView.Adapter<ListTantanganWideRiwayatAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemTantanganRiwayatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tantangan: Tantangan, position:Int){
            if(tantangan.kunci_jawaban == "False"){
                binding.tvRiwayatApproved.text = "gagal"
                val textColor = R.color.danger
                val textColored = ContextCompat.getColor(itemView.context, textColor)
                binding.tvRiwayatApproved.setTextColor(textColored)
                val backgroundColor = R.color.light_danger
                val backgroundColored = ContextCompat.getColor(itemView.context, backgroundColor)
                binding.tvRiwayatApproved.setBackgroundColor(backgroundColored)
            }
            else{
                binding.tvRiwayatApproved.text = "berhasil"
                binding.tvRiwayatApproved.setTextColor(ContextCompat.getColor(itemView.context, R.color.success))
                binding.tvRiwayatApproved.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.light_success))
            }
            binding.tvRiwayatJudulTantangan.text = tantangan.nama
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
        val binding = ItemTantanganRiwayatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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