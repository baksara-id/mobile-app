package com.baksara.app.ui.profil.bantuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baksara.app.R

class BantuanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Bantuan"
    }
}