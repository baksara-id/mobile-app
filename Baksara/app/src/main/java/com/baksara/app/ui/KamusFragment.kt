package com.baksara.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baksara.app.R
import com.baksara.app.databinding.FragmentKamusBinding

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kamus, container, false)
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