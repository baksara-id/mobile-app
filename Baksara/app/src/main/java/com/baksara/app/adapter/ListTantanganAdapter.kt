package com.baksara.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.databinding.ItemTantanganBinding
import com.baksara.app.response.Tantangan
import com.baksara.app.ui.tantangan.SoalTantanganActivity

class ListTantanganAdapter(private val tantangans: List<Tantangan>): RecyclerView.Adapter<ListTantanganAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemTantanganBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tantangan: Tantangan, position:Int){
            binding.tvTantanganName.text =tantangan.nama

            val jumlahXp = tantangan.exp.toString()
            binding.tvTantanganExp.text = "$jumlahXp XP"
            binding.btnIkuti.setOnClickListener {
                val intent = Intent(itemView.context, SoalTantanganActivity::class.java)
                intent.putExtra(SoalTantanganActivity.TANTANGAN_ID, tantangan.id)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemTantanganBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tantangan = tantangans[position]
        holder.bind(tantangan, position)
    }

    override fun getItemCount(): Int = tantangans.size
}