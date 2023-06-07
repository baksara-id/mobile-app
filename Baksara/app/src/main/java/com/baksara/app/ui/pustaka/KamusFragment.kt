package com.baksara.app.ui.pustaka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.baksara.app.ViewModelFactory
import com.baksara.app.adapter.ListKamusAdapter
import com.baksara.app.database.KamusBelajar
import com.baksara.app.databinding.FragmentKamusBinding

class KamusFragment : Fragment() {
    private var _binding: FragmentKamusBinding? = null
    private val binding get() = _binding!!
    private lateinit var kamusViewModel: KamusViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKamusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        kamusViewModel = ViewModelProvider(this, viewModelFactory)[KamusViewModel::class.java]

        kamusViewModel.getAllKamusBelajar().observe(requireActivity()){
            if (it.isNotEmpty()){
                setupListKamus(it)
            }else{

            }
        }
    }

    private fun setupListKamus(listKamusBelajar: List<KamusBelajar>){
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvListkamus.layoutManager = layoutManager
        val adapter = ListKamusAdapter(listKamusBelajar)
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