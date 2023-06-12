package com.baksara.app.ui.soal.baca

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.database.SoalBaca
import com.baksara.app.databinding.FragmentBacaBinding
import com.baksara.app.ui.MainActivity
import com.baksara.app.ui.soal.SoalActivity
import com.baksara.app.ui.soal.gambar.GambarFragment

class BacaFragment : Fragment() {
    private var _binding: FragmentBacaBinding? = null
    private val binding get() = _binding!!
    private lateinit var bacaViewModel: BacaViewModel

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
        val modulId = arguments?.getInt(MODUL_ID) ?: 0

        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        bacaViewModel = ViewModelProvider(this, viewModelFactory)[BacaViewModel::class.java]

        bacaViewModel.getSoalBacaByPelajaran(pelajaranId, nomorUrutan).observe(requireActivity()){
            binding.tvAksaraSoalBaca.text = it.aksara
            binding.tvLatinSoalBaca.text = it.latin
            binding.fabMulaiSoalBaca.setOnClickListener {

                fillProgressBar()

                val bundle = Bundle()
                bundle.putInt(GambarFragment.PELAJARAN_ID, pelajaranId)
                bundle.putInt(GambarFragment.URUTAN_SOAL, nomorUrutan)
                bundle.putInt(GambarFragment.MODUL_ID, modulId)

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

    private fun fillProgressBar(){
        val progressBar = (requireActivity() as SoalActivity).binding.progressSoal
        progressBar.progress += 1
    }

    companion object {
        var MODUL_ID = "modul_id"
        var PELAJARAN_ID = "pelajaran_id"
        var URUTAN_SOAL = "soal_id"
    }
}