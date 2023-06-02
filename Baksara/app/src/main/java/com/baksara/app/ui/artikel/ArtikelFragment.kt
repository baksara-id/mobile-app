package com.baksara.app.ui.artikel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.adapter.ListArtikelAdapter
import com.baksara.app.adapter.ListModulAdapter
import com.baksara.app.databinding.FragmentArtikelBinding
import com.baksara.app.helper.InitialDataSource


class ArtikelFragment : Fragment() {
    private var _binding: FragmentArtikelBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtikelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArtikelAdapter()
    }

    private fun setupArtikelAdapter(){
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvArtikel.layoutManager = layoutManager
        val adapter = ListArtikelAdapter(InitialDataSource.getArtikels())
        binding.rvArtikel.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArtikelFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}