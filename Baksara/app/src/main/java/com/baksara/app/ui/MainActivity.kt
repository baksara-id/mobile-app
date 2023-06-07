package com.baksara.app.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.ActivityMainBinding
import com.baksara.app.local.UserPreferences
import com.baksara.app.response.Langganan
import com.baksara.app.response.RiwayatBelajar
import com.baksara.app.response.User
import com.baksara.app.ui.scanner.ScannerActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val viewModelFactory = ViewModelFactory.getInstance(this)
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        bottomNavigationSetup()
        binding.fabScanner.setOnClickListener {
            startCameraX()
        }

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun bottomNavigationSetup(){
        val navView: BottomNavigationView = binding.bottomNav

        navController = findNavController(R.id.nav_host_fragment)

        navView.menu.getItem(2).isEnabled = false
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_kelas, R.id.navigation_pustaka, R.id.navigation_scanner, R.id.navigation_artikel, R.id.navigation_profil
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun startCameraX() {
        val intent = Intent(this, ScannerActivity::class.java)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10

        const val PREF = "android_user"
        const val CURRENTLIMIT = "limit"//jumlah scan
        const val EXP = "exp"
        const val LEVEL = "level"
        const val AVATAR = "profilepic"
        const val UNIQUEID = "id"
        const val FULLNAME = "fullname"
        const val EMAIL = "email"
        const val PREMIUM = "langganan"
        const val MODUL = "modul"
        const val KELAS = "pelajaran"
        const val TOKEN = "tokenz"
    }
}