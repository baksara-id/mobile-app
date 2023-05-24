package com.baksara.app.ui.soal.pilihan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.baksara.app.R
import com.baksara.app.database.SoalPilihan
import com.baksara.app.databinding.FragmentPilihanBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.ui.soal.Hasil.HasilFragment
import com.baksara.app.ui.soal.baca.BacaFragment

class PilihanFragment : Fragment() {

    private var _binding: FragmentPilihanBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        val soal = soalPilihan(pelajaranId, nomorUrutan)

        binding.tvAksaraSoalPilihan.text = soal.aksara
        binding.btnPilihan1.text = soal.pilihanSatu
        binding.btnPilihan2.text = soal.pilihanDua
        binding.btnPilihan3.text = soal.pilihanTiga
        binding.btnPilihan4.text = soal.pilihanEmpat

        binding.btnPilihan1.setOnClickListener {
            onMenjawab(binding.btnPilihan1.text.toString(),soal.jawabanBenar, nomorUrutan, pelajaranId )
        }

        binding.btnPilihan2.setOnClickListener {
            onMenjawab(binding.btnPilihan2.text.toString(),soal.jawabanBenar, nomorUrutan, pelajaranId )
        }

        binding.btnPilihan3.setOnClickListener {
            onMenjawab(binding.btnPilihan3.text.toString(),soal.jawabanBenar, nomorUrutan, pelajaranId )
        }

        binding.btnPilihan4.setOnClickListener {
            onMenjawab(binding.btnPilihan4.text.toString(),soal.jawabanBenar, nomorUrutan, pelajaranId )
        }


    }

    fun onMenjawab(pilihan: String, jawaban:String, nomorUrutan:Int, pelajaranId: Int){
        if (pilihan == jawaban){
            if(nomorUrutan != 5){
                val bundle = Bundle()
                bundle.putInt(PELAJARAN_ID, pelajaranId)
                bundle.putInt(URUTAN_SOAL, nomorUrutan + 1)

                val pilihanFragment = PilihanFragment()
                pilihanFragment.arguments = bundle
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(R.id.frame_pelajaran, pilihanFragment, PilihanFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
            }else{
                val hasilFragment = HasilFragment()
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(R.id.frame_pelajaran, hasilFragment, HasilFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
            }
        }else{
            Toast.makeText(requireContext(), "Jawaban Masih Salah", Toast.LENGTH_SHORT).show()
        }
    }

    fun soalPilihan(pelajaranId: Int, nomor: Int): SoalPilihan {
        return InitialDataSource.getSoalPilihan().first{
            it.pelajaranId == pelajaranId && it.urutan == nomor
        }
    }

    companion object {
        var PELAJARAN_ID = "pelajaran_id"
        var URUTAN_SOAL = "soal_id"
    }
}