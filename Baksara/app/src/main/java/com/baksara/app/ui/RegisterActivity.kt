package com.baksara.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Patterns
import android.widget.Toast
import com.baksara.app.databinding.ActivityRegisterBinding
import com.baksara.app.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.tvTologin.text = Html.fromHtml("<u>Login</u>", Html.FROM_HTML_MODE_LEGACY)

        binding.btnRegister.setOnClickListener {
            binding.btnRegister.isEnabled = false
            val password = binding.inputPassword.text.toString()
            val repassword = binding.inputKonfirmasiPassword.text.toString()
            val passwordLength = binding.inputPassword.length()
            val namaLengkap = binding.inputNamaLengkap.text.toString()
            val email = binding.inputEmail.text.toString()
            if(!password.isEmpty() && !repassword.isEmpty() && !namaLengkap.isEmpty() && !email.isEmpty()){
                if(passwordLength >= 8 && isValidEmail(email)) {
//                    val client = ApiConfig.getApiService().registerUser(name,email,password)
//                    client.enqueue(object: Callback<RegisterResponse> {
//                        override fun onResponse(
//                            call: Call<RegisterResponse>,
//                            response: Response<RegisterResponse>
//                        ) {
//                            if(response.isSuccessful){
//                                response.body()?.let { callback.onRegisterResponse(it)}
//                            }
//                            else{
//                                val registerFailed = RegisterResponse(
//                                    true,
//                                    "Register has Failed!"
//                                )
//                                callback.onRegisterResponse(registerFailed)
//                            }
//                        }
//
//                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                            val registerFailed = RegisterResponse(
//                                true,
//                                t.message.toString()
//                            )
//                            callback.onRegisterResponse(registerFailed)
//                        }
//                    })

                    binding.btnRegister.isEnabled = true
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
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
}