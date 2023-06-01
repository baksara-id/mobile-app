package com.baksara.app.ui.pustaka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.adapter.ListCeritaAdapter
import com.baksara.app.databinding.FragmentCeritaBinding
import com.baksara.app.helper.InitialDataSource

class CeritaFragment : Fragment() {
    private var _binding: FragmentCeritaBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCeritaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCeritaAdapter()
    }

    private fun setupCeritaAdapter(){
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvCerita.layoutManager = layoutManager
        val adapter = ListCeritaAdapter(InitialDataSource.getCeritas())
        binding.rvCerita.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CeritaFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}