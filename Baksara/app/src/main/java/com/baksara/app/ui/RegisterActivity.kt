package com.baksara.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.ActivityRegisterBinding
import com.baksara.app.network.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.tvTologin.text = Html.fromHtml("<u>Login</u>", Html.FROM_HTML_MODE_LEGACY)
        homeViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[MainViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            binding.btnRegister.isEnabled = false
            val password = binding.inputPassword.text.toString()
            val repassword = binding.inputKonfirmasiPassword.text.toString()
            val passwordLength = binding.inputPassword.length()
            val namaLengkap = binding.inputNamaLengkap.text.toString()
            val email = binding.inputEmail.text.toString()
            if(binding.checkBox.isChecked == false) {
                Toast.makeText(this@RegisterActivity, "Anda belum menyetujui pernyataan kami", Toast.LENGTH_SHORT).show()
                binding.btnRegister.isEnabled = true
                return@setOnClickListener
            }
            if(password != repassword) Toast.makeText(this@RegisterActivity, "Password tidak sama dengan Repassword", Toast.LENGTH_SHORT).show()

            if(!password.isEmpty() && !repassword.isEmpty() && !namaLengkap.isEmpty() && !email.isEmpty()){
                if(passwordLength >= 8 && isValidEmail(email)) {
                    lifecycleScope.launchWhenStarted {
                        launch {
                            homeViewModel.register(email, namaLengkap, password).collect { response ->
                                binding.btnRegister.isEnabled = true
                                response.onSuccess {
                                    if (it.errors == null) {
                                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                        startActivity(intent)
                                        Toast.makeText(this@RegisterActivity, "Berhasil Register", Toast.LENGTH_SHORT).show()
                                        finish()
                                    } else {
                                        Toast.makeText(this@RegisterActivity, it.errors[0].message, Toast.LENGTH_SHORT).show()
                                    }
                                }

                                response.onFailure { error ->
                                    Toast.makeText(this@RegisterActivity, "Terjadi error pada aplikasi", Toast.LENGTH_SHORT).show()
                                    Log.e("error", error.message.toString())
                                }
                            }
                        }
                    }
                }
                else if(passwordLength >= 8 && !isValidEmail(email)) {
                    Toast.makeText(
                        this,
                        "Email harus memiliki format email!",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.btnRegister.isEnabled = true
                }
                else if (isValidEmail(email) && passwordLength < 8){
                    Toast.makeText(
                        this,
                        "Password harus memiliki 8 karakter!",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.btnRegister.isEnabled = true
                }
                else {
                    Toast.makeText(
                        this,
                        "Password harus 8 karakter dan Email harus memiliki format email",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.btnRegister.isEnabled = true
                }
            }
            else{
                Toast.makeText(
                    this,
                    "Terdapat data yang kosong!",
                    Toast.LENGTH_SHORT
                ).show()
                binding.btnRegister.isEnabled = true
            }
        }
        binding.tvTologin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isValidEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}