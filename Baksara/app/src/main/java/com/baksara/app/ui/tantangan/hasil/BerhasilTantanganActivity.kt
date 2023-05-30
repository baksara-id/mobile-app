package com.baksara.app.ui.tantangan.hasil

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import com.baksara.app.R
import com.baksara.app.databinding.ActivityBerhasilTantanganBinding
import com.baksara.app.databinding.ActivityLoginBinding
import com.baksara.app.databinding.FragmentBerhasilBinding

class BerhasilTantanganActivity : AppCompatActivity() {
    private var _binding: ActivityBerhasilTantanganBinding? = null
    private val binding get() = _binding!!
    private lateinit var fadeInAnimator: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBerhasilTantanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fadeInAnimator = ObjectAnimator.ofFloat(binding.tvExpBerhasilTantangan, "alpha", 0f, 1f)
            .apply {
                duration = 2000
                doOnEnd { binding.tvExpBerhasilTantangan.visibility = View.VISIBLE }
            }

        fadeInAnimator.start()

        fadeInAnimator = ObjectAnimator.ofFloat(binding.tvDeskripsiBerhasilTantangan, "alpha", 0f, 1f)
            .apply {
                duration = 2000
                doOnEnd { binding.tvDeskripsiBerhasilTantangan.visibility = View.VISIBLE }
            }

        fadeInAnimator.start()


        binding.btnKembaliTantangan.setOnClickListener {
            finish()
        }
    }
}