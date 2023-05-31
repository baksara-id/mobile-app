package com.baksara.app.ui.profil.pencapaian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baksara.app.R

class PencapaianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencapaian)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pencapaian"
    }
}