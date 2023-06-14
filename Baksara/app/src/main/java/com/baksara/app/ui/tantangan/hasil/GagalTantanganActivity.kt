package com.baksara.app.ui.tantangan.hasil

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import com.baksara.app.R
import com.baksara.app.databinding.ActivityBerhasilTantanganBinding
import com.baksara.app.databinding.ActivityGagalTantanganBinding

class GagalTantanganActivity : AppCompatActivity() {
    private var _binding: ActivityGagalTantanganBinding? = null
    private val binding get() = _binding!!
    private lateinit var fadeInAnimator: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGagalTantanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fadeInAnimator = ObjectAnimator.ofFloat(binding.tvDeskripsiGagalTantangan, "alpha", 0f, 1f)
            .apply {
                duration = 2000
                doOnEnd { binding.tvDeskripsiGagalTantangan.visibility = View.VISIBLE }
            }

        fadeInAnimator.start()

        binding.btnKembaliGagalTantangan.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}