package com.baksara.app.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.database.Modul
import com.baksara.app.databinding.ItemModuleBinding
import com.baksara.app.ui.MainActivity
import com.baksara.app.ui.kelas.KelasActivity

class ListModulAdapter(private val moduls: List<Modul>): RecyclerView.Adapter<ListModulAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemModuleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(modul: Modul, position:Int){
            binding.tvItemJudul.text = "Modul " + modul.nomor
            binding.tvItemDetail.text = modul.deskripsi
            binding.tvItemLogo.text = modul.url_background

            if(modul.terkunci && !modul.selesai){
                binding.constraintModul.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_locked_module)
            }else{
                binding.root.setOnClickListener {
                    val intent = Intent(itemView.context, KelasActivity::class.java)
                    intent.putExtra(KelasActivity.MODUL_ID, modul.id)
                    intent.putExtra(KelasActivity.MODUL_NAMA, modul.deskripsi)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemModuleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val modul = moduls[position]
        holder.bind(modul, position)
    }

    override fun getItemCount(): Int = moduls.size
}