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
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.databinding.ActivityRegisterBinding
import com.baksara.app.utils.ToastUtils
import kotlinx.coroutines.launch

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
                ToastUtils.showToast(this@RegisterActivity, "Anda belum menyetujui pernyataan kami")
                binding.btnRegister.isEnabled = true
                return@setOnClickListener
            }
            if(password != repassword) ToastUtils.showToast(this@RegisterActivity, "Password tidak sama dengan repassword")

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
                                        ToastUtils.showToast(this@RegisterActivity, "Berhasil Register!")
                                        finish()
                                    } else {
                                        ToastUtils.showToast(this@RegisterActivity, it.errors[0].message.toString())
                                    }
                                }

                                response.onFailure { error ->
                                    ToastUtils.showToast(this@RegisterActivity, "Terjadi error pada aplikasi")
                                    Log.e("error", error.message.toString())
                                }
                            }
                        }
                    }
                }
                else if(passwordLength >= 8 && !isValidEmail(email)) {
                    ToastUtils.showToast(this@RegisterActivity, "Email harus memiliki format email!")
                    binding.btnRegister.isEnabled = true
                }
                else if (isValidEmail(email) && passwordLength < 8){
                    ToastUtils.showToast(this@RegisterActivity, "Password harus memiliki 8 karakter!")
                    binding.btnRegister.isEnabled = true
                }
                else {
                    ToastUtils.showToast(this@RegisterActivity, "Password harus 8 karakter dan\n Email harus memiliki format email")
                    binding.btnRegister.isEnabled = true
                }
            }
            else{
                ToastUtils.showToast(this@RegisterActivity, "Terdapat data yang kosong!")
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