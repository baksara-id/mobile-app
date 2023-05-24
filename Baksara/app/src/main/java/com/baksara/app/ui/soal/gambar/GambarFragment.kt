package com.baksara.app.ui.soal.gambar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baksara.app.R
import com.baksara.app.database.SoalGambar
import com.baksara.app.databinding.FragmentGambarBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.ui.soal.baca.BacaFragment
import com.baksara.app.ui.soal.pilihan.PilihanFragment

class GambarFragment : Fragment() {

    private var _binding: FragmentGambarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGambarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pelajaranId = arguments?.getInt(BacaFragment.PELAJARAN_ID) ?: 0
        val nomorUrutan = arguments?.getInt(BacaFragment.URUTAN_SOAL) ?: 0

        val soal = soalGambar(pelajaranId, nomorUrutan)

        binding.tvLatinSoalGambar.text = soal.latin
        binding.btnJawabSoalGambar.setOnClickListener {
            if(nomorUrutan != 5){
                val bundle = Bundle()
                bundle.putInt(PELAJARAN_ID, pelajaranId)
                bundle.putInt(URUTAN_SOAL, nomorUrutan + 1)

                val gambarFragment = GambarFragment()
                gambarFragment.arguments = bundle
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(R.id.frame_pelajaran, gambarFragment, GambarFragment::class.java.simpleName)
                    commit()
                }
            }else{
                val bundle = Bundle()
                bundle.putInt(PilihanFragment.PELAJARAN_ID, pelajaranId)
                bundle.putInt(PilihanFragment.URUTAN_SOAL, 1)

                val pilihanFragment = PilihanFragment()
                pilihanFragment.arguments = bundle
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(R.id.frame_pelajaran, pilihanFragment, PilihanFragment::class.java.simpleName)
                    commit()
                }
            }
        }
    }

    fun soalGambar(pelajaranId: Int, nomor: Int): SoalGambar {
        return InitialDataSource.getSoalGambar().first{
            it.pelajaranId == pelajaranId && it.urutan == nomor
        }
    }

    companion object {
        var PELAJARAN_ID = "pelajaran_id"
        var URUTAN_SOAL = "soal_id"
    }
}