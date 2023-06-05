package com.baksara.app.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.ViewModelFactory
import com.baksara.app.adapter.ListModulAdapter
import com.baksara.app.adapter.ListTantanganAdapter
import com.baksara.app.database.Modul
import com.baksara.app.databinding.FragmentHomeBinding
import com.baksara.app.helper.InitialDataSource.getModuls
import com.baksara.app.helper.InitialDataSource.getTantangans
import com.baksara.app.ui.MainViewModel
import com.baksara.app.ui.tantangan.TantanganActivity


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        homeViewModel.getAllModul().observe(requireActivity()){ moduls ->
            if(moduls.isNotEmpty()){
                setupModulAdapter(moduls)
            }else{

            }
        }

        setupTantanganAdapter()
        binding.btnTantanganSelengkapnya.setOnClickListener {
            val intent = Intent(activity, TantanganActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupTantanganAdapter(){
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTantangan.layoutManager = layoutManager

        val adapter = ListTantanganAdapter(getTantangans())
        binding.rvTantangan.adapter = adapter
    }

    private fun setupModulAdapter(moduls: List<Modul>){
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvModul.layoutManager = layoutManager
        val adapter = ListModulAdapter(moduls)
        binding.rvModul.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}