package com.baksara.app.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.database.Modul
import com.baksara.app.databinding.ItemDialogInformationBinding
import com.baksara.app.databinding.ItemModuleBinding
import com.baksara.app.ui.kelas.KelasActivity

class ListModulAdapter(private val moduls: List<Modul>): RecyclerView.Adapter<ListModulAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemModuleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var modulSelesaiBefore = false
        fun bind(modul: Modul, position: Int) {
            binding.tvItemJudul.text = "Modul " + modul.nomor
            binding.tvItemDetail.text = modul.deskripsi
            binding.tvItemLogo.text = modul.url_background

            Log.d("terkunci", modul.terkunci.toString())

            if (modul.id != 1 && (modul.terkunci || !moduls[position-1].selesai)) {
                binding.constraintModul.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.bg_locked_module)
                binding.root.setOnClickListener {
                    showLockedModuleDialog(itemView.context, modul.selesai, modul.terkunci)
                }
            } else {
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

    private fun showLockedModuleDialog(context: Context, selesai: Boolean, terkunci: Boolean) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_information, null)


        val imgInformation: ImageView = dialogView.findViewById(R.id.img_information)
        val textTitle: TextView = dialogView.findViewById(R.id.tv_information_title)
        val textDesc: TextView = dialogView.findViewById(R.id.tv_information_description)
        val buttonInformation: Button = dialogView.findViewById(R.id.btn_information)

        imgInformation.setImageResource(R.drawable.img_logo_information)
        textTitle.text = "Pesan Informasi"


        if (!selesai) {
            textDesc.text = "Selesaikan modul sebelumnya untuk memulai modul ini."
            buttonInformation.text = "Mengerti"

        } else if (terkunci) {
            textDesc.text = "Lakukan pembelian langganan untuk melanjutkan modul ini."
            buttonInformation.text = "Beli Langganan"
        }

        builder.setView(dialogView)
        val dialog: AlertDialog = builder.create()
        buttonInformation.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}