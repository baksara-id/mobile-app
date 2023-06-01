package com.baksara.app.local

import android.content.Context
import com.baksara.app.ui.scanner.ScannerActivity

class UserPreferences constructor(context: Context) {
    private val userPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun setTrueIsSeen(){
        val editor = userPref.edit()
        editor.putBoolean(IS_SEEN, true)
        editor.apply()
    }

    fun getIsSeen(): Boolean{
        return userPref.getBoolean(IS_SEEN, false)
    }

    fun getCurrentLimit(): Int{
        return userPref.getInt(CURRENTLIMIT,0)
    }

    fun setCurrentLimit(currentLimit: Int){
        val editor = userPref.edit()
        editor.putInt(CURRENTLIMIT, currentLimit)
        editor.apply()
    }

    fun getLimitReach(): Boolean{
        return userPref.getBoolean(LIMITREACH, false)
    }

    fun setLimitReach(){
        val editor = userPref.edit()
        editor.putBoolean(LIMITREACH, true)
        editor.apply()
    }

    companion object{
        private const val PREF = "android_user"
        private const val LIMITREACH = "limitreach"
        private const val CURRENTLIMIT = "limit"
        private const val IS_SEEN = "is_seen"

        @Volatile
        private var instance: UserPreferences? = null

        fun getInstance(context: Context): UserPreferences = instance ?: synchronized(this){
            instance ?: UserPreferences(context)
        }
    }
}