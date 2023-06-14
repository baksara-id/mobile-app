package com.baksara.app.ui.soal.pilihan

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.databinding.FragmentPilihanBinding
import com.baksara.app.ui.soal.Hasil.BerhasilFragment
import com.baksara.app.ui.soal.Hasil.GagalFragment
import com.baksara.app.ui.soal.SoalActivity
import com.baksara.app.ui.soal.baca.BacaFragment

class PilihanFragment : Fragment() {

    private var _binding: FragmentPilihanBinding? = null
    private val binding get() = _binding!!
    private lateinit var pilihanViewModel: PilihanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPilihanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pelajaranId = arguments?.getInt(BacaFragment.PELAJARAN_ID) ?: 0
        val nomorUrutan = arguments?.getInt(BacaFragment.URUTAN_SOAL) ?: 0
        val modulId = arguments?.getInt(BacaFragment.MODUL_ID) ?: 0

        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        pilihanViewModel = ViewModelProvider(this, viewModelFactory)[PilihanViewModel::class.java]

        pilihanViewModel.getSoalPilihanByPelajaran(pelajaranId, nomorUrutan).observe(requireActivity()){ soalPilihan ->
            binding.tvAksaraSoalPilihan.text = soalPilihan.aksara
            binding.btnPilihan1.text = soalPilihan.pilihanSatu
            binding.btnPilihan2.text = soalPilihan.pilihanDua
            binding.btnPilihan3.text = soalPilihan.pilihanTiga
            binding.btnPilihan4.text = soalPilihan.pilihanEmpat

            binding.btnPilihan1.setOnClickListener {
                onMenjawab(binding.btnPilihan1.text.toString(),soalPilihan.jawabanBenar, nomorUrutan, pelajaranId, binding.btnPilihan1, modulId )
            }

            binding.btnPilihan2.setOnClickListener {
                onMenjawab(binding.btnPilihan2.text.toString(),soalPilihan.jawabanBenar, nomorUrutan, pelajaranId, binding.btnPilihan2,modulId )
            }

            binding.btnPilihan3.setOnClickListener {
                onMenjawab(binding.btnPilihan3.text.toString(),soalPilihan.jawabanBenar, nomorUrutan, pelajaranId, binding.btnPilihan3,modulId )
            }

            binding.btnPilihan4.setOnClickListener {
                onMenjawab(binding.btnPilihan4.text.toString(),soalPilihan.jawabanBenar, nomorUrutan, pelajaranId, binding.btnPilihan4,modulId )
            }
        }
    }

    fun onMenjawab(pilihan: String, jawaban:String, nomorUrutan:Int, pelajaranId: Int, button: Button, modulId: Int){
        fillProgressBar()
        disableButton()

        val bundle = Bundle()
        bundle.putInt(PELAJARAN_ID, pelajaranId)
        bundle.putInt(URUTAN_SOAL, nomorUrutan + 1)
        bundle.putInt(MODUL_ID, modulId)

        button.setTextColor(resources.getColor(R.color.accent_white))
        if (pilihan == jawaban) {
            updateJawabanBenar()
            button.background.setTint(resources.getColor(R.color.success))
        }else {
            button.background.setTint(resources.getColor(R.color.danger))
        }

        // Pengecekan Benar Salah dan Lanjut Soal
        if(nomorUrutan != 5){
            val pilihanFragment = PilihanFragment()
            pilihanFragment.arguments = bundle
            val fragmentManager = parentFragmentManager

            Handler().postDelayed({
                binding.root.isClickable = false
                binding.root.isFocusable = false
                fragmentManager.beginTransaction().apply {
                    replace(
                        R.id.frame_pelajaran,
                        pilihanFragment,
                        PilihanFragment::class.java.simpleName
                    )
                    addToBackStack(null)
                    commit()
                }
            }, 1000)
        }else{
            // Cek jumlah benar
            val berhasil = if(getJawabanBenar() >= 2) true else false
            if(berhasil)
            {
                // Berhasil update riwayat belajar
                val berhasilFragment = BerhasilFragment()
                berhasilFragment.arguments = bundle
                val fragmentManager = parentFragmentManager

                Handler().postDelayed({
                    fragmentManager.beginTransaction().apply {
                        replace(R.id.frame_pelajaran, berhasilFragment, BerhasilFragment::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }
                }, 1000)
            }
            else{
                // Gagal do nothing
                val gagalFragment = GagalFragment()
                val fragmentManager = parentFragmentManager

                Handler().postDelayed({
                    fragmentManager.beginTransaction().apply {
                        replace(R.id.frame_pelajaran, gagalFragment, GagalFragment::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }
                }, 1000)
            }
        }
    }

    private fun disableButton(){
        binding.btnPilihan1.isEnabled = false
        binding.btnPilihan1.isClickable = false
        binding.btnPilihan2.isEnabled = false
        binding.btnPilihan2.isClickable = false
        binding.btnPilihan3.isEnabled = false
        binding.btnPilihan3.isClickable = false
        binding.btnPilihan4.isEnabled = false
        binding.btnPilihan4.isClickable = false
    }

    private fun fillProgressBar(){
        val progressBar = (requireActivity() as SoalActivity).binding.progressSoal
        progressBar.progress += 1
    }

    private fun updateJawabanBenar(){
        SoalActivity.totalJawabanBenar += 1
    }

    private fun getJawabanBenar() : Int{
        return SoalActivity.totalJawabanBenar
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