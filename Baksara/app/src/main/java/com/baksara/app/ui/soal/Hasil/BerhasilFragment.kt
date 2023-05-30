package com.baksara.app.ui.soal.Hasil

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import com.baksara.app.databinding.FragmentBerhasilBinding


class BerhasilFragment : Fragment() {

    private var _binding: FragmentBerhasilBinding? = null
    private val binding get() = _binding!!
    private lateinit var fadeInAnimator: ObjectAnimator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBerhasilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fadeInAnimator = ObjectAnimator.ofFloat(binding.tvBerhasilExp, "alpha", 0f, 1f)
            .apply {
                duration = 2000
                doOnEnd { binding.tvBerhasilExp.visibility = View.VISIBLE }
            }

        fadeInAnimator.start()

        binding.btnKembaliKelas.setOnClickListener {
            activity?.finish()
        }
    }
}