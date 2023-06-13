package com.baksara.app.ui.soal

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.baksara.app.R
import com.baksara.app.database.SoalBaca
import com.baksara.app.database.SoalGambar
import com.baksara.app.database.SoalPilihan
import com.baksara.app.databinding.ActivitySoalBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.ui.LoginActivity
import com.baksara.app.ui.soal.baca.BacaFragment

class SoalActivity : AppCompatActivity() {
    private var _binding: ActivitySoalBinding? = null
    val binding get() = _binding!!
    var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Ambil pelajaran ID dan modul ID
        val pelajaranId = intent.getIntExtra(PELAJARAN_ID, 0)
        val modulId = intent.getIntExtra(MODUL_ID, 0)

        // Create bundle to send data
        val bundle = Bundle()
        bundle.putInt(BacaFragment.PELAJARAN_ID, pelajaranId)
        bundle.putInt(BacaFragment.MODUL_ID, modulId)
        bundle.putInt(BacaFragment.URUTAN_SOAL, 1)

        val fragmentManager = supportFragmentManager
        val bacaFragment = BacaFragment()
        bacaFragment.arguments = bundle
        val fragment = fragmentManager.findFragmentByTag(BacaFragment::class.java.simpleName)
        if (fragment !is BacaFragment) {
            Log.d("FragmentPelajaran", "Fragment Name :" + BacaFragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_pelajaran, bacaFragment, BacaFragment::class.java.simpleName)
                .commit()
        }

    }
    private fun showDialogKeluar(context: Context) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_information, null)


        val imgInformation: ImageView = dialogView.findViewById(R.id.img_information)
        val textTitle: TextView = dialogView.findViewById(R.id.tv_information_title)
        val textDesc: TextView = dialogView.findViewById(R.id.tv_information_description)
        val buttonInformation: Button = dialogView.findViewById(R.id.btn_information)

        imgInformation.setImageResource(R.drawable.img_logo_danger_information)
        textTitle.text = "Pesan Peringatan"
        textDesc.text = "Apakah anda yakin ingin keluar dari kelas?"
        buttonInformation.text = "Keluar"
        buttonInformation.background.setTint(resources.getColor(R.color.danger))

        builder.setView(dialogView)
        dialog = builder.create()
        buttonInformation.setOnClickListener {
            finish()
        }

        dialog?.show()
    }

    override fun onBackPressed() {
        showDialogKeluar(this)
    }

    override fun onDestroy() {
        dialog?.dismiss()
        _binding = null
        super.onDestroy()
    }

    companion object {
        var totalJawabanBenar = 0
        var MODUL_ID = "modul_id"
        const val PELAJARAN_ID = "pelajaran_id"
    }
}