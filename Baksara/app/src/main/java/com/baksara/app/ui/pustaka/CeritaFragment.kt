package com.baksara.app.ui.pustaka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.ViewModelFactory
import com.baksara.app.adapter.ListCeritaAdapter
import com.baksara.app.databinding.FragmentCeritaBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.response.Cerita
import com.baksara.app.ui.MainViewModel

class CeritaFragment : Fragment() {
    private var _binding: FragmentCeritaBinding? = null
    private val binding get() = _binding!!
    private lateinit var ceritaViewModel: PustakaViewModel
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

        ceritaViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext()))[PustakaViewModel::class.java]

        ceritaViewModel.liveDataCerita.observe(requireActivity()){ result ->
            if(isAdded){
                result.onSuccess {
                    val listCerita = it.data?.ceritas?: emptyList()
                    setupCeritaAdapter(listCerita)
                }
                result.onFailure {
                    // Kasih tau Error
                }
            }
        }

        ceritaViewModel.liveDataIsLoading.observe(requireActivity()){
            showLoading(it)
        }
    }

    override fun onResume() {
        super.onResume()
        setupCeritaAdapter(emptyList())
        ceritaViewModel.fetchAllCerita()
    }

    fun showLoading(isLoading: Boolean){
        binding.loadingCerita.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun setupCeritaAdapter(ceritas: List<Cerita>){
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvCerita.layoutManager = layoutManager
        val adapter = ListCeritaAdapter(ceritas)
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