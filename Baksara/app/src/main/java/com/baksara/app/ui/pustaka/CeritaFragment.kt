package com.baksara.app.ui.pustaka

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.adapter.ListCeritaAdapter
import com.baksara.app.databinding.FragmentCeritaBinding
import com.baksara.app.response.Cerita

class CeritaFragment : Fragment() {
    private var _binding: FragmentCeritaBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding is null. Fragment is not attached or onDestroyView() has been called.")
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
        binding.inputCariCerita.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()
                val filteredList = ceritas.filter {
                    it.judul?.lowercase()?.contains(query) == true
                }
                adapter.setListCerita(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
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