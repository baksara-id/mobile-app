package com.baksara.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabsPustaka, binding.viewPager) { tab, position ->
            // Customize tab titles based on position
            when (position) {
                0 -> tab.text = getString(R.string.tab_cerita)
                1 -> tab.text = getString(R.string.tab_cerita)
                2 -> tab.text = getString(R.string.tab_translator)
            }
        }.attach()

        return view
    }

}