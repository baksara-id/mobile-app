package com.baksara.app.ui.soal.gambar

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.database.SoalGambar
import com.baksara.app.databinding.FragmentGambarBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.ui.soal.SoalActivity
import com.baksara.app.ui.soal.baca.BacaFragment
import com.baksara.app.ui.soal.baca.BacaViewModel
import com.baksara.app.ui.soal.pilihan.PilihanFragment

class GambarFragment : Fragment() {

    private var _binding: FragmentGambarBinding? = null
    private val binding get() = _binding!!
    private lateinit var gambarViewModel: GambarViewModel

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

        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        gambarViewModel = ViewModelProvider(this, viewModelFactory)[GambarViewModel::class.java]

        gambarViewModel.getSoalGambarByPelajaran(pelajaranId, nomorUrutan).observe(requireActivity()){
            binding.tvLatinSoalGambar.text = it.latin
            binding.btnHapusSoalGambar.setOnClickListener {
                binding.drawViewAksara.clear()
            }

            binding.btnJawabSoalGambar.setOnClickListener {
                fillProgressBar()

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

    }

    private fun fillProgressBar(){
        val progressBar = (requireActivity() as SoalActivity).binding.progressSoal
        progressBar.progress += 1
    }

    companion object {
        var PELAJARAN_ID = "pelajaran_id"
        var URUTAN_SOAL = "soal_id"
    }
}