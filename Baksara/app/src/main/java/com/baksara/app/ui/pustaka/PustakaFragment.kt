package com.baksara.app.ui.pustaka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.baksara.app.R
import com.baksara.app.adapter.PustakaPagerAdapter
import com.baksara.app.databinding.FragmentPustakaBinding
import com.google.android.material.tabs.TabLayoutMediator

class PustakaFragment : Fragment() {

    private var _binding: FragmentPustakaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPustakaBinding.inflate(inflater, container, false)
        val view = binding.root

        val adapter = PustakaPagerAdapter(this)
        binding.viewPagerPustaka.adapter = adapter

        TabLayoutMediator(binding.tabsPustaka, binding.viewPagerPustaka) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_cerita)
                1 -> tab.text = getString(R.string.tab_kamus)
                2 -> tab.text = getString(R.string.tab_translator)
            }
        }.attach()

        for (i in 0 .. 2){
            val displayText = LayoutInflater.from(requireContext()).inflate(R.layout.layout_tabtitle, null) as TextView
            binding.tabsPustaka.getTabAt(i)?.customView = displayText
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}