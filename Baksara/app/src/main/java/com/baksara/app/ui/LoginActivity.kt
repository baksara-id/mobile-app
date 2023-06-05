package com.baksara.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Patterns
import android.widget.Toast
import com.baksara.app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

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
            if(passwordLength >= 8 && isValidEmail(email)) {
                binding.btnLogin.isEnabled = true
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
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