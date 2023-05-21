package com.baksara.app.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.baksara.app.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val preferences = this.getSharedPreferences(OnBoardingActivity.PREFNAME, Context.MODE_PRIVATE)

        val delay = 4000.0.toLong()
        val seenBoarding = preferences.getBoolean(OnBoardingActivity.IS_SEEN, false)

        var intent = Intent(this, OnBoardingActivity::class.java)

        if (seenBoarding){
            intent = Intent(this, LoginActivity::class.java)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, delay)
    }


}