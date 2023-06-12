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
        private val SPLASHDELAY: Long = 4000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val preferences = this.getSharedPreferences(OnBoardingActivity.PREFNAME, Context.MODE_PRIVATE)

        val seenBoarding = preferences.getBoolean(OnBoardingActivity.IS_SEEN, false)
        val loggedIn = preferences.getString(MainActivity.TOKEN, "") ?: ""

        var intent = Intent(this, OnBoardingActivity::class.java)

        if (seenBoarding){
            if(loggedIn != "") intent = Intent(this, MainActivity::class.java)
            else intent = Intent(this, LoginActivity::class.java)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, SPLASHDELAY)
    }


}