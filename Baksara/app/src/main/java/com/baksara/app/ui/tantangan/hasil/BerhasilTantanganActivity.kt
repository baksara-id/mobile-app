package com.baksara.app.ui.tantangan.hasil

import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.databinding.ActivityBerhasilTantanganBinding
import com.baksara.app.response.User
import com.baksara.app.ui.MainActivity
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
        val currentLevel = userPref.getInt(MainActivity.LEVEL, 1)
        val currentEXP = userPref.getInt(MainActivity.EXP, 0)
        var earn = expEarned + currentEXP
        var expNeededToLevelUp = calculateEXPNeeded(currentLevel)
        var counter = currentLevel
        while(earn >= expNeededToLevelUp){
            counter += 1 // Ini akan jadi patokan untuk menghitung kenaikan level
            earn = earn - expNeededToLevelUp // Ini akan jadi sisa setelah iterasi berakhir
            expNeededToLevelUp = counter * 500
        }
        val levelIncrease = counter - currentLevel
        if(levelIncrease > 0)
        {
            // Panggil update exp + level
            // Earn akan digunakan untuk update CurrentEXP di db
            // counter akan digunakan untuk update level di db
            userId.let{
                tantanganViewModel.fetchResponseUpdateUserLevel(counter,it,earn)
            }
        }
        else{
            // Pangill update exp
            userId.let{
                tantanganViewModel.fetchResponseUpdateUserExp(earn, it)
            }
        }


        tantanganViewModel.liveDataResponseUpdateUserEXP.observe(this){ response ->
            response.onSuccess {
                val user = it.data?.update
                setUser(user, userPref)
            }
            response.onFailure {
                // Kalau Gagal
            }
        }
        tantanganViewModel.liveDataResponseUpdateUserLevelEXP.observe(this){ response ->
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
        editor.putInt(MainActivity.UNIQUEID, user?.id?:0)
        editor.putInt(MainActivity.EXP, user?.exp?:0)
        editor.putInt(MainActivity.LEVEL, user?.level?:0)
        editor.apply()
    }

    fun calculateEXPNeeded(nextLevel: Int): Int //this function is used to calculate next Level EXP needed
    {
        val expNeededNow = nextLevel * nextLevel * 250 + nextLevel * 250
        val currentLevel = nextLevel - 1
        val expCummulative = currentLevel * currentLevel * 250 + currentLevel * 250
        return expNeededNow - expCummulative
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val USERID = "user_id"
        const val EXPEARNED = "expdidapat"
    }
}