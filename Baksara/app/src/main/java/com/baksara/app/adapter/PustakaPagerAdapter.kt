package com.baksara.app.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.baksara.app.ui.pustaka.CeritaFragment
import com.baksara.app.ui.pustaka.KamusFragment
import com.baksara.app.ui.pustaka.TranslatorFragment

class PustakaPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = CeritaFragment()
            1 -> fragment = KamusFragment()
            2 -> fragment = TranslatorFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
        return fragment
    }

}