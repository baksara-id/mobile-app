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
import androidx.recyclerview.widget.RecyclerView
import com.baksara.app.R
import com.baksara.app.database.Pelajaran
import com.baksara.app.databinding.ItemPelajaranBinding
import com.baksara.app.ui.kelas.KelasActivity
import com.baksara.app.ui.soal.SoalActivity

class ListPelajaranAdapter(private val pelajarans: List<Pelajaran>, private val modulID: Int): RecyclerView.Adapter<ListPelajaranAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemPelajaranBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pelajaran: Pelajaran, position:Int){
            binding.tvJudul.text = "Pelajaran " + pelajaran.nomor
            binding.tvAksaraPelajaran.text = pelajaran.deskripsiAksara
            binding.tvDeskripsi.text = pelajaran.deskripsiLatin

            if(pelajaran.terkunci && !pelajaran.selesai){
                binding.constraintPelajaran.alpha = 0.5f
                binding.fabMulai.isClickable = false
                binding.root.setOnClickListener {
                   showLockedPelajaranDialog(itemView.context)
                }
            }else{
                binding.fabMulai.backgroundTintList = ContextCompat.getColorStateList(itemView.context, R.color.success)
                binding.fabMulai.setOnClickListener {
                    val intent = Intent(itemView.context, SoalActivity::class.java)
                    intent.putExtra(SoalActivity.PELAJARAN_ID, pelajaran.id)
                    intent.putExtra(SoalActivity.MODUL_ID, modulID)
                    itemView.context.startActivity(intent)
                }
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

    private fun showLockedPelajaranDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_information, null)


        val imgInformation: ImageView = dialogView.findViewById(R.id.img_information)
        val textTitle: TextView = dialogView.findViewById(R.id.tv_information_title)
        val textDesc: TextView = dialogView.findViewById(R.id.tv_information_description)
        val buttonInformation: Button = dialogView.findViewById(R.id.btn_information)

        imgInformation.setImageResource(R.drawable.img_logo_information)
        textTitle.text = "Pesan Informasi"

        textDesc.text = "Selesaikan pelajaran sebelumnya untuk memulai modul ini."
        buttonInformation.text = "Mengerti"

        builder.setView(dialogView)
        val dialog: AlertDialog = builder.create()
        buttonInformation.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}