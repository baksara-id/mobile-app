package com.baksara.app.ui.pustaka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baksara.app.R
import com.baksara.app.databinding.FragmentTranslatorBinding

class TranslatorFragment : Fragment() {
    private var _binding: FragmentTranslatorBinding? = null
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_translator, container, false)
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