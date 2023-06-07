package com.baksara.app.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.database.KamusBelajar
import com.baksara.app.databinding.ItemKamusBinding
import com.baksara.app.ui.pustaka.DetailKamusActivity

class ListKamusAdapter(private val kamus: List<KamusBelajar>): RecyclerView.Adapter<ListKamusAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemKamusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kamus: KamusBelajar, position: Int) {
            binding.tvItemJudul.text = kamus.judul
            binding.tvItemDetail.text = kamus.jumlah
            binding.tvItemLogo.text = kamus.aksara

            if(kamus.terkunci){
                binding.constraintKamusAksara.backgroundTintList =  ContextCompat.getColorStateList(itemView.context, R.color.neutral_200)
                binding.tvItemJudul.alpha = 0.8f
                binding.tvItemDetail.alpha = 0.8f
                binding.cardViewKamusAksara.setOnClickListener {
                    showLockedKamusDialog(itemView.context)
                }
            }else{
                binding.cardViewKamusAksara.setOnClickListener {
                    val intent = Intent(itemView.context, DetailKamusActivity::class.java)
                    intent.putExtra(DetailKamusActivity.KAMUSID, kamus.id)
                    intent.putExtra(DetailKamusActivity.KAMUSJUDUL, kamus.judul)
                    intent.putExtra(DetailKamusActivity.KAMUSDESKRIPSI, kamus.deskripsi)
                    itemView.context.startActivity(intent)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKamusAdapter.ListViewHolder {
        val binding = ItemKamusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListKamusAdapter.ListViewHolder, position: Int) {
        val materiKamus = kamus[position]
        holder.bind(materiKamus, position)
    }

    override fun getItemCount(): Int = kamus.size

    private fun showLockedKamusDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_information, null)


        val imgInformation: ImageView = dialogView.findViewById(R.id.img_information)
        val textTitle: TextView = dialogView.findViewById(R.id.tv_information_title)
        val textDesc: TextView = dialogView.findViewById(R.id.tv_information_description)
        val buttonInformation: Button = dialogView.findViewById(R.id.btn_information)

        imgInformation.setImageResource(R.drawable.img_logo_information)
        textTitle.text = "Pesan Informasi"

        textDesc.text = "Lakukan pembelian langganan untuk melanjutkan modul ini."
        buttonInformation.text = "Beli Langganan"

        builder.setView(dialogView)
        val dialog: AlertDialog = builder.create()
        buttonInformation.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}