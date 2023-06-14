package com.baksara.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.database.InAppBanner
import com.baksara.app.databinding.ItemAppbannerBinding
import com.baksara.app.response.Tantangan
import com.baksara.app.ui.tantangan.SoalTantanganActivity

class ListInAppBannerAdapter(private val appbanners: List<InAppBanner>): RecyclerView.Adapter<ListInAppBannerAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemAppbannerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: InAppBanner, position:Int){
            binding.imgBanner.setImageResource(R.drawable.arjunadummy2)
            binding.imgBanner.setOnClickListener {
                Toast.makeText(itemView.context,"Pergi ke Halaman ${banner.url_tujuan}",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListInAppBannerAdapter.ListViewHolder {
        val binding = ItemAppbannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListInAppBannerAdapter.ListViewHolder, position: Int) {
        val appbanner = appbanners[position]
        holder.bind(appbanner, position)
    }

    override fun getItemCount(): Int = appbanners.size
}