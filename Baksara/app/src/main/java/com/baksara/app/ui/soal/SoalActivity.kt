package com.baksara.app.ui.soal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.baksara.app.R
import com.baksara.app.database.SoalBaca
import com.baksara.app.database.SoalGambar
import com.baksara.app.database.SoalPilihan
import com.baksara.app.databinding.ActivitySoalBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.ui.soal.baca.BacaFragment

class SoalActivity : AppCompatActivity() {
    private var _binding: ActivitySoalBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySoalBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    companion object {
        var totalJawabanBenar = 0
        var MODUL_ID = "modul_id"
        const val PELAJARAN_ID = "pelajaran_id"
    }
}