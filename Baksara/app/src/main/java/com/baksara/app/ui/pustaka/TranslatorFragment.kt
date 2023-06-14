package com.baksara.app.ui.pustaka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.databinding.FragmentTranslatorBinding
import com.baksara.app.utils.ToastUtils

class TranslatorFragment : Fragment() {
    private var _binding: FragmentTranslatorBinding? = null
    private val binding get() = _binding!!
    private lateinit var translatorViewModel: PustakaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTranslatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        translatorViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext()))[PustakaViewModel::class.java]
        binding.btnTranslate.setOnClickListener {
            val inputString = binding.inputAksaraLatin.text
            translatorViewModel.fetchTranslatorResult(inputString.toString())
        }

        translatorViewModel.liveDataTranslatorResponse.observe(requireActivity()){ result ->
            result.onSuccess {
                val hasilTranslator = it.hasil
                if(hasilTranslator != null){
                    binding.tvAksaraJawa.text = hasilTranslator
                }
                else ToastUtils.showToast(requireActivity(), "Translator tidak bisa mengenali tulisan anda")
            }
            result.onFailure {
                // Kasih tau Error
            }
        }

        binding.btnHapusTranslate.setOnClickListener {
            binding.inputAksaraLatin.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TranslatorFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}