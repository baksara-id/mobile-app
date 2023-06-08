package com.baksara.app.ui.tantangan.hasil

import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.ActivityBerhasilTantanganBinding
import com.baksara.app.databinding.ActivityLoginBinding
import com.baksara.app.databinding.FragmentBerhasilBinding
import com.baksara.app.local.UserPreferences
import com.baksara.app.response.User
import com.baksara.app.ui.MainActivity
import com.baksara.app.ui.tantangan.SoalTantanganActivity
import com.baksara.app.ui.tantangan.TantanganViewModel

class BerhasilTantanganActivity : AppCompatActivity() {
    private var _binding: ActivityBerhasilTantanganBinding? = null
    private val binding get() = _binding!!
    private lateinit var fadeInAnimator: ObjectAnimator
    private lateinit var tantanganViewModel: TantanganViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBerhasilTantanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userPref = getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)

        tantanganViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@BerhasilTantanganActivity))[TantanganViewModel::class.java]
        val userId = intent.getIntExtra(USERID, 0)
        val expEarned = intent.getIntExtra(EXPEARNED, 0)
        userId.let{
            tantanganViewModel.fetchResponseUpdateUser()
        }
        tantanganViewModel.liveDataResponseUpdateUser.observe(this){ response ->
            response.onSuccess {
                val user = it.data?.update
                setUser(user, userPref)
            }
            response.onFailure {
                // Kalau Gagal
            }
        }

        binding.tvExpBerhasilTantangan.text = "+ $expEarned XP"

        fadeInAnimator = ObjectAnimator.ofFloat(binding.tvExpBerhasilTantangan, "alpha", 0f, 1f)
            .apply {
                duration = 2000
                doOnEnd { binding.tvExpBerhasilTantangan.visibility = View.VISIBLE }
            }

        fadeInAnimator.start()

        fadeInAnimator = ObjectAnimator.ofFloat(binding.tvDeskripsiBerhasilTantangan, "alpha", 0f, 1f)
            .apply {
                duration = 2000
                doOnEnd { binding.tvDeskripsiBerhasilTantangan.visibility = View.VISIBLE }
            }

        fadeInAnimator.start()

        fadeInAnimator = ObjectAnimator.ofFloat(binding.btnKembaliTantangan, "alpha", 0f, 1f)
            .apply {
                duration = 2000
                doOnEnd { binding.tvDeskripsiBerhasilTantangan.visibility = View.VISIBLE }
            }

        fadeInAnimator.start()

        binding.btnKembaliTantangan.setOnClickListener {
            finish()
        }

    }

    fun setUser(user: User?, userPref : SharedPreferences){
        val editor = userPref.edit()
        editor.putString(MainActivity.FULLNAME, user?.name)
        editor.putString(MainActivity.EMAIL, user?.email)
        editor.putString(MainActivity.AVATAR, user?.avatar)
        editor.putInt(MainActivity.UNIQUEID, user?.id?:0)
        editor.putInt(MainActivity.EXP, user?.exp?:0)
        editor.putInt(MainActivity.LEVEL, user?.level?:0)
        editor.putInt(MainActivity.CURRENTLIMIT, user?.jumlah_scan?:0)
        editor.putInt(MainActivity.PREMIUM, user?.langganan?.id?:0)
        editor.putString(MainActivity.TOKEN,user?.token)
        editor.apply()
    }

    companion object{
        const val USERID = "user_id"
        const val EXPEARNED = "expdidapat"
    }
}