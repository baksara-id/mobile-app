package com.baksara.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.database.Pelajaran
import com.baksara.app.databinding.ItemPelajaranBinding
import com.baksara.app.ui.kelas.KelasActivity
import com.baksara.app.ui.soal.SoalActivity

class ListPelajaranAdapter(private val pelajarans: List<Pelajaran>): RecyclerView.Adapter<ListPelajaranAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemPelajaranBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pelajaran: Pelajaran, position:Int){
            binding.tvJudul.text = "Pelajaran " + pelajaran.nomor
            binding.tvAksaraPelajaran.text = pelajaran.deskripsiAksara
            binding.tvDeskripsi.text = pelajaran.deskripsiLatin

            if(pelajaran.terkunci && !pelajaran.selesai){
                binding.constraintPelajaran.alpha = 0.5f
                binding.fabMulai.isClickable = false
            }else{
                binding.fabMulai.backgroundTintList = ContextCompat.getColorStateList(itemView.context, R.color.success)
                binding.fabMulai.setOnClickListener {
                    val intent = Intent(itemView.context, SoalActivity::class.java)
                    intent.putExtra(SoalActivity.PELAJARAN_ID, pelajaran.id)
                    itemView.context.startActivity(intent)                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemPelajaranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val pelajaran = pelajarans[position]
        holder.bind(pelajaran, position)
    }

    override fun getItemCount(): Int = pelajarans.size
}