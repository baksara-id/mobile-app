package com.baksara.app.ui.profil.laporkanmasalah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baksara.app.R

class LaporkanMasalahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporkan_masalah)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Laporkan Masalah"
    }
}