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
import com.baksara.app.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        homeViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[MainViewModel::class.java]

        binding.tvToregister.text = Html.fromHtml("<u>Register</u>", Html.FROM_HTML_MODE_LEGACY)
        binding.tvToregister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            binding.btnLogin.isEnabled = false
            val passwordLength = binding.inputLoginPassword.length()
            val email = binding.inputLoginEmail.text.toString()
            val password = binding.inputLoginPassword.text.toString()
            if(passwordLength >= 8 && isValidEmail(email)) {
                lifecycleScope.launchWhenStarted {
                    launch {
                        homeViewModel.login(email, password).collect { response ->
                            binding.btnLogin.isEnabled = true
                            response.onSuccess {
                                if (it.errors == null) {
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                    Toast.makeText(this@LoginActivity, "Testing Token ${it.data.toString()}", Toast.LENGTH_SHORT).show()
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@LoginActivity, it.errors[0].message, Toast.LENGTH_SHORT).show()
                                }
                            }

                            response.onFailure { error ->
                                Toast.makeText(this@LoginActivity, "Terjadi error pada aplikasi", Toast.LENGTH_SHORT).show()
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
                binding.btnLogin.isEnabled = true
            }
            else if (isValidEmail(email) && passwordLength < 8){
                Toast.makeText(
                    this,
                    "Password harus memiliki 8 karakter!",
                    Toast.LENGTH_SHORT
                ).show()
                binding.btnLogin.isEnabled = true
            }
            else {
                Toast.makeText(
                    this,
                    "Password harus 8 karakter dan Email harus memiliki format email",
                    Toast.LENGTH_SHORT
                ).show()
                binding.btnLogin.isEnabled = true
            }
        }

    }

    private fun isValidEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}