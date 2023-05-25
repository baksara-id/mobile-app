package com.baksara.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baksara.app.R
import com.baksara.app.databinding.FragmentCeritaBinding

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cerita, container, false)
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