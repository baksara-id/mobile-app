package com.baksara.app.ui.kelas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.adapter.ListModulAdapter
import com.baksara.app.adapter.ListTantanganAdapter
import com.baksara.app.databinding.FragmentKelasBinding
import com.baksara.app.helper.InitialDataSource.getModuls
import com.baksara.app.helper.InitialDataSource.getTantangans


class KelasFragment : Fragment() {
    private var _binding: FragmentKelasBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKelasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTantanganAdapter()
        setupModulAdapter()
    }

    private fun setupTantanganAdapter(){
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTantangan.layoutManager = layoutManager

        val adapter = ListTantanganAdapter(getTantangans())
        binding.rvTantangan.adapter = adapter
    }

    private fun setupModulAdapter(){
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvModul.layoutManager = layoutManager
        val adapter = ListModulAdapter(getModuls())
        binding.rvModul.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}