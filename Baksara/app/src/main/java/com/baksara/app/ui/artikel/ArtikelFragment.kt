package com.baksara.app.ui.artikel

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
import com.baksara.app.adapter.ListArtikelAdapter
import com.baksara.app.databinding.FragmentArtikelBinding
import com.baksara.app.response.Artikel


class ArtikelFragment : Fragment() {
    private var _binding: FragmentArtikelBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding is null. Fragment is not attached or onDestroyView() has been called.")
    private lateinit var artikelViewModel: ArtikelViewModel
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
        artikelViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext()))[ArtikelViewModel::class.java]
        artikelViewModel.liveDataArtikel.observe(requireActivity()){result->
            if(isAdded){
                result.onSuccess {
                    val listArtikel = it.data?.artikels?: emptyList()
                    setupArtikelAdapter(listArtikel)
                }
                result.onFailure {
                    // Kalau gagal
                }
            }
        }
        artikelViewModel.liveDataIsLoading.observe(requireActivity()){
            showLoading(it)
        }

    }

    override fun onResume() {
        super.onResume()
        setupArtikelAdapter(emptyList())
        artikelViewModel.fetchAllArtikel()
    }

    fun showLoading(isLoading: Boolean){
        binding.loadingArtikel.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun setupArtikelAdapter(listArtikel: List<Artikel>){
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvArtikel.layoutManager = layoutManager
        val adapter = ListArtikelAdapter(listArtikel)
        binding.rvArtikel.adapter = adapter
        binding.inputArtikelSearch.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()
                val filteredList = listArtikel.filter {
                    it.judul?.lowercase()?.contains(query) == true
                }
                adapter.setListArtikel(filteredList)
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
        fun newInstance(param1: String, param2: String) =
            ArtikelFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}