package com.baksara.app.ui.soal.gambar

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.databinding.FragmentGambarBinding
import com.baksara.app.ui.kelas.KelasActivity
import com.baksara.app.ui.soal.SoalActivity
import com.baksara.app.ui.soal.baca.BacaFragment
import com.baksara.app.ui.soal.pilihan.PilihanFragment
import com.baksara.app.utils.ToastUtils

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
        val modulId = arguments?.getInt(BacaFragment.MODUL_ID) ?: 0

        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        gambarViewModel = ViewModelProvider(this, viewModelFactory)[GambarViewModel::class.java]

        gambarViewModel.getSoalGambarByPelajaran(pelajaranId, nomorUrutan).observe(requireActivity()){ soalGambar ->
            binding.tvLatinSoalGambar.text = soalGambar.latin
            binding.btnHapusSoalGambar.setOnClickListener {
                binding.drawViewAksara.clear()
            }


            binding.btnJawabSoalGambar.setOnClickListener {
                binding.btnJawabSoalGambar.isEnabled = false
                binding.btnHapusSoalGambar.isEnabled = false
                fillProgressBar()

                val actualClass = KelasActivity.modulNama?.lowercase() + "_" + soalGambar.latin.lowercase()

                // predict image
                val imageFile = binding.drawViewAksara.saveDrawingToFile()
                if (imageFile != null) {
                    gambarViewModel.fetchPredictImage(imageFile, actualClass)
                }

                gambarViewModel.liveDataPredict.observe(requireActivity()){ result ->
                    result.onSuccess {
                        //cek predict
                        val responseMLDouble = it.result?.toDouble() ?: 0.0
                        if (responseMLDouble >= 0.65){
                            updateJawabanBenar()
                            binding.cvDrawAksara.strokeColor = resources.getColor(R.color.success)
                        }else{
                            binding.cvDrawAksara.strokeColor = resources.getColor(R.color.danger)
                        }

                        //ganti transisi ke kelas baca atau ke kelas pilihan
                        if(nomorUrutan != 5){
                            val bundle = Bundle()
                            bundle.putInt(PELAJARAN_ID, pelajaranId)
                            bundle.putInt(URUTAN_SOAL, nomorUrutan + 1)
                            bundle.putInt(MODUL_ID, modulId)

                            val bacaFragment = BacaFragment()
                            bacaFragment.arguments = bundle
                            val fragmentManager = parentFragmentManager

                            Handler().postDelayed({
                                binding.root.isClickable = false
                                binding.root.isFocusable = false
                                fragmentManager.beginTransaction().apply {
                                    replace(R.id.frame_pelajaran, bacaFragment, BacaFragment::class.java.simpleName)
                                    addToBackStack(null)
                                    commit()
                                }
                            }, 1000)


                        }else{
                            val bundle = Bundle()
                            bundle.putInt(PilihanFragment.PELAJARAN_ID, pelajaranId)
                            bundle.putInt(PilihanFragment.URUTAN_SOAL, 1)
                            bundle.putInt(PilihanFragment.MODUL_ID, modulId)

                            val pilihanFragment = PilihanFragment()
                            pilihanFragment.arguments = bundle
                            val fragmentManager = parentFragmentManager

                            Handler().postDelayed({
                                binding.root.isClickable = false
                                binding.root.isFocusable = false
                                fragmentManager.beginTransaction().apply {
                                    replace(R.id.frame_pelajaran, pilihanFragment, PilihanFragment::class.java.simpleName)
                                    addToBackStack(null)
                                    commit()
                                }
                            }, 1000)
                        }
                    }
                    result.onFailure {
                        // Kasih tau Error
                    }
                }
            }
        }

    }

    private fun fillProgressBar(){
        val progressBar = (requireActivity() as SoalActivity).binding.progressSoal
        progressBar.progress += 1
    }

    private fun updateJawabanBenar(){
        SoalActivity.totalJawabanBenar += 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var MODUL_ID = "modul_id"
        var PELAJARAN_ID = "pelajaran_id"
        var URUTAN_SOAL = "soal_id"
    }
}