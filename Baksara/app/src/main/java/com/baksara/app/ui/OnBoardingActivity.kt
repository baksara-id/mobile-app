package com.baksara.app.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baksara.app.databinding.ActivityOnboardingBinding

class OnBoardingActivity : AppCompatActivity(){
    private var _binding: ActivityOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val preferences = this.getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)

        binding.tvSkip.setOnClickListener {
            val editor = preferences.edit()
            editor.putBoolean(IS_SEEN, true)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish()
        }

        binding.btnMulai.setOnClickListener {
            val editor = preferences.edit()
            editor.putBoolean(IS_SEEN, true)
            editor.apply()

            binding.btnMulai.isEnabled = false
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val IS_SEEN = "is_seen"
    }
}