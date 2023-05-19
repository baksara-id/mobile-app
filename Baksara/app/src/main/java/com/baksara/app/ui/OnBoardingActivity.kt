package com.baksara.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baksara.app.R
import com.baksara.app.databinding.ActivityOnboardingBinding

class OnBoardingActivity : AppCompatActivity(){
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        supportActionBar?.hide()

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnMulai.setOnClickListener {
                binding.btnMulai.isEnabled = false
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
        }


    }
}