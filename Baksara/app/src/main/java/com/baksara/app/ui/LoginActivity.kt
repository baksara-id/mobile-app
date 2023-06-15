package com.baksara.app.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.baksara.app.R
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.databinding.ActivityLoginBinding
import com.baksara.app.response.User
import com.baksara.app.utils.ToastUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: MainViewModel
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val userPref = getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
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
                                    setUser(it.data?.loginUser, userPref)
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                    ToastUtils.showToast(this@LoginActivity, "Berhasil Login!")
                                    startActivity(intent)
                                    finish()
                                } else {
                                    ToastUtils.showToast(this@LoginActivity, it.errors[0].message.toString())
                                }
                            }

                            response.onFailure { error ->
                                ToastUtils.showToast(this@LoginActivity, "Terjadi error pada aplikasi")
                                Log.e("error", error.message.toString())
                            }
                        }
                    }
                }
            }
            else if(passwordLength >= 8 && !isValidEmail(email)) {
                ToastUtils.showToast(this, "Email harus memiliki format email!")
                binding.btnLogin.isEnabled = true
            }
            else if (isValidEmail(email) && passwordLength < 8){
                ToastUtils.showToast(this, "Password harus memiliki 8 karakter!")
                binding.btnLogin.isEnabled = true
            }
            else {
                ToastUtils.showToast(this, "Password harus 8 karakter dan \nEmail harus memiliki format email")
                binding.btnLogin.isEnabled = true
            }
        }

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestIdToken(coba)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.googleButton.setOnClickListener {
            signInGoogleAuth()
        }
    }

    fun signInGoogleAuth(){
        Log.d("checkpoint 1", "masuk")
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("checkpoint 2", "masuk")
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                ToastUtils.showToast(this, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                ToastUtils.showToast(this, "Google sign in failed $e")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    ToastUtils.showToast(this, "signInWithCredential:success")
                    val user = auth.currentUser
                    Log.d("email",auth.currentUser?.email.toString())
                    Log.d("nama",auth.currentUser?.displayName.toString())
                    Log.d("avatar",auth.currentUser?.photoUrl.toString())
                } else {
                    // If sign in fails, display a message to the user.
                    ToastUtils.showToast(this, "signInWithCredential:failure")

                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    fun setUser(user: User?, userPreferences: SharedPreferences){
        val editor = userPreferences.edit()
        val jumlahScan = user?.jumlah_scan?:0
        editor.putString(MainActivity.FULLNAME, user?.name)
        editor.putString(MainActivity.EMAIL, user?.email)
        editor.putString(MainActivity.AVATAR, user?.avatar)
        editor.putInt(MainActivity.UNIQUEID, user?.id?:0)
        editor.putInt(MainActivity.EXP, user?.exp?:0)
        editor.putInt(MainActivity.LEVEL, user?.level?:0)
        editor.putInt(MainActivity.CURRENTLIMIT, jumlahScan)
        editor.putInt(MainActivity.PREMIUM, user?.langganan?.id?:1)
        editor.putInt(MainActivity.KELAS, user?.riwayatBelajar?.get(0)?.nomor_pelajaran ?:0)
        editor.putInt(MainActivity.MODUL, user?.riwayatBelajar?.get(0)?.nomor_modul ?:0)
        editor.putString(MainActivity.TOKEN,user?.token)
        if(jumlahScan >= 3){
            editor.putBoolean(MainActivity.LIMITREACH, true)
        }
        else{
            editor.putBoolean(MainActivity.LIMITREACH, false)
        }
        editor.apply()
    }


    private fun isValidEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}