package com.baksara.app.local

import android.content.Context
import com.baksara.app.response.Langganan
import com.baksara.app.response.RiwayatBelajar
import com.baksara.app.response.User
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

    fun setUser(user: User){
        val editor = userPref.edit()
        editor.putString(FULLNAME, user.name)
        editor.putString(EMAIL, user.email)
        editor.putString(AVATAR, user.avatar)
        editor.putInt(UNIQUEID, user.id?:0)
        editor.putInt(EXP, user.exp?:0)
        editor.putInt(LEVEL, user.level?:0)
        editor.putInt(CURRENTLIMIT, user.jumlah_scan?:0)
        editor.putInt(PREMIUM, user.langganan?.id?:0)
        editor.putInt(KELAS, user.riwayatBelajar?.nomor_pelajaran ?:0)
        editor.putInt(MODUL, user.riwayatBelajar?.nomor_modul ?:0)
        editor.putString(TOKEN,user.token)
        editor.apply()
    }

    fun getUser(): User{
        val name = userPref.getString(FULLNAME,"")
        val email = userPref.getString(EMAIL,"")
        val avatar = userPref.getString(AVATAR,"")
        val id = userPref.getInt(UNIQUEID,0)
        val exp = userPref.getInt(EXP,0)
        val level = userPref.getInt(LEVEL,0)
        val limit = userPref.getInt(CURRENTLIMIT,0)
        val kelas = userPref.getInt(KELAS,0)
        val modul = userPref.getInt(MODUL,0)
        val token = userPref.getString(TOKEN,"")
        val langganan = userPref.getInt(PREMIUM,0)
        val _langgananObject = Langganan(langganan,"",0.0,0)
        val _riwayatBelajarObject = RiwayatBelajar(0,id,modul,kelas)
        return User(id,_langgananObject,name,email,token,avatar, exp,level,limit,kadaluarsa = null,null,_riwayatBelajarObject)
    }

    fun deleteUser(){
        val editor = userPref.edit()
        editor.remove(FULLNAME)
        editor.remove(EMAIL)
        editor.remove(AVATAR)
        editor.remove(UNIQUEID)
        editor.remove(EXP)
        editor.remove(LEVEL)
        editor.remove(CURRENTLIMIT)
        editor.remove(PREMIUM)
        editor.remove(KELAS)
        editor.remove(MODUL)
        editor.remove(TOKEN)
        editor.apply()
    }

    companion object{
        private const val PREF = "android_user"
        private const val LIMITREACH = "limitreach"
        private const val CURRENTLIMIT = "limit"//jumlah scan
        private const val IS_SEEN = "is_seen"
        private const val EXP = "exp"
        private const val LEVEL = "level"
        private const val AVATAR = "profilepic"
        private const val UNIQUEID = "id"
        private const val FULLNAME = "fullname"
        private const val EMAIL = "email"
        private const val PREMIUM = "langganan"
        private const val MODUL = "modul"
        private const val KELAS = "pelajaran"
        private const val TOKEN = "tokenz"

        @Volatile
        private var instance: UserPreferences? = null

        fun getInstance(context: Context): UserPreferences = instance ?: synchronized(this){
            instance ?: UserPreferences(context)
        }
    }
}