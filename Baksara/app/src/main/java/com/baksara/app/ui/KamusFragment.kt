package com.baksara.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.baksara.app.adapter.ListKamusAdapter
import com.baksara.app.databinding.FragmentKamusBinding
import com.baksara.app.helper.InitialDataSource

class KamusFragment : Fragment() {
    private var _binding: FragmentKamusBinding? = null
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
        _binding = FragmentKamusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListKamus()
    }

    private fun setupListKamus(){
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvListkamus.layoutManager = layoutManager
        val adapter = ListKamusAdapter(InitialDataSource.getListKamus())
        binding.rvListkamus.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            KamusFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}