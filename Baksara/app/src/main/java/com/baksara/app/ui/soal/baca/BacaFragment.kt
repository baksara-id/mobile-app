package com.baksara.app.ui.soal.baca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baksara.app.R
import com.baksara.app.database.SoalBaca
import com.baksara.app.databinding.FragmentBacaBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.ui.soal.gambar.GambarFragment

class BacaFragment : Fragment() {
    private var _binding: FragmentBacaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBacaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pelajaranId = arguments?.getInt(PELAJARAN_ID) ?: 0
        val nomorUrutan = arguments?.getInt(URUTAN_SOAL) ?: 0

        val soal = soalBaca(pelajaranId, nomorUrutan)

        binding.tvAksaraSoalBaca.text = soal.aksara
        binding.tvLatinSoalBaca.text = soal.latin
        binding.fabMulaiSoalBaca.setOnClickListener {
            if(nomorUrutan != 5){
                val bundle = Bundle()
                bundle.putInt(PELAJARAN_ID, pelajaranId)
                bundle.putInt(URUTAN_SOAL, nomorUrutan + 1)

                val bacaFragment = BacaFragment()
                bacaFragment.arguments = bundle
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(R.id.frame_pelajaran, bacaFragment, BacaFragment::class.java.simpleName)
                    commit()
                }
            }else{
                val bundle = Bundle()
                bundle.putInt(GambarFragment.PELAJARAN_ID, pelajaranId)
                bundle.putInt(GambarFragment.URUTAN_SOAL, 1)

                val gambarFragment = GambarFragment()
                gambarFragment.arguments = bundle
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(R.id.frame_pelajaran, gambarFragment, GambarFragment::class.java.simpleName)
                    commit()
                }
            }
        }
    }

    fun soalBaca(pelajaranId: Int, nomor: Int): SoalBaca {
        return InitialDataSource.getSoalBaca().first{
            it.pelajaranId == pelajaranId && it.urutan == nomor
        }
    }

    companion object {
        var PELAJARAN_ID = "pelajaran_id"
        var URUTAN_SOAL = "soal_id"
    }
}