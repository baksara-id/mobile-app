package com.baksara.app.ui.soal.Hasil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baksara.app.R
import com.baksara.app.databinding.FragmentGambarBinding
import com.baksara.app.databinding.FragmentHasilBinding
import com.baksara.app.databinding.FragmentPilihanBinding
import com.baksara.app.ui.kelas.KelasActivity

class HasilFragment : Fragment() {

    private var _binding: FragmentHasilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHasilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnKembaliKelas.setOnClickListener {
            activity?.finish()
        }
    }
}