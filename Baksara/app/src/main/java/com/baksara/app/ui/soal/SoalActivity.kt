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
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pelajaranId = intent.getIntExtra(PELAJARAN_ID, 0)

        val bundle = Bundle()
        bundle.putInt(BacaFragment.PELAJARAN_ID, pelajaranId)
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

    fun getSoalBaca(pelajaranId: Int, nomor: Int): SoalBaca{
        return InitialDataSource.getSoalBaca().first{
            it.pelajaranId == pelajaranId && it.urutan == nomor
        }
    }

    fun getListSoalGambarByPelajaran(pelajaranId: Int): List<SoalGambar>{
        val listPelajaran: MutableList<SoalGambar> = mutableListOf()

        InitialDataSource.getSoalGambar().forEach { soalGambar ->
            if (soalGambar.pelajaranId == pelajaranId){
                listPelajaran.add(soalGambar)
            }
        }

        return listPelajaran
    }

    fun getListSoalPilihanByPelajaran(pelajaranId: Int): List<SoalPilihan>{
        val listPelajaran: MutableList<SoalPilihan> = mutableListOf()

        InitialDataSource.getSoalPilihan().forEach { soalPilihan ->
            if (soalPilihan.pelajaranId == pelajaranId){
                listPelajaran.add(soalPilihan)
            }
        }

        return listPelajaran
    }

    companion object {
        const val PELAJARAN_ID = "pelajaran_id"
    }
}