package com.baksara.app.ui.profil

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.FragmentProfilBinding
import com.baksara.app.local.UserPreferences
import com.baksara.app.response.Langganan
import com.baksara.app.response.RiwayatBelajar
import com.baksara.app.response.User
import com.baksara.app.ui.LoginActivity
import com.baksara.app.ui.MainActivity
import com.baksara.app.ui.profil.bantuan.BantuanActivity
import com.baksara.app.ui.profil.langganan.LanggananActivity
import com.baksara.app.ui.profil.laporkanmasalah.LaporkanMasalahActivity
import com.baksara.app.ui.profil.pencapaian.PencapaianActivity
import com.baksara.app.ui.profil.tentangaplikasi.TentangAplikasiActivity
import com.baksara.app.ui.tantangan.hasil.BerhasilTantanganActivity
import com.bumptech.glide.Glide

class ProfilFragment : Fragment() {
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var userPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPencapaian.setOnClickListener {
            val intent = Intent(activity, PencapaianActivity::class.java)
            startActivity(intent)
        }
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        profileViewModel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
        userPref = requireActivity().getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        val userLogin = getUser()

        if(userLogin.langganan?.id != 1){
            // User Premium
            binding.cvBadgeUser.backgroundTintList = ContextCompat.getColorStateList(requireActivity(), R.color.light_premium)
            binding.badgeUser.text = "User Premium"
            binding.badgeUser.setBackgroundResource(R.drawable.bg_border_premium)
            binding.badgeUser.setTextColor(ContextCompat.getColor(requireActivity(), R.color.premium))
        }
        else{
            // User Standard
            binding.cvBadgeUser.backgroundTintList = ContextCompat.getColorStateList(requireActivity(), R.color.light_standard)
            binding.badgeUser.text = "User Standard"
            binding.badgeUser.setBackgroundResource(R.drawable.bg_border_standard)
            binding.badgeUser.setTextColor(ContextCompat.getColor(requireActivity(), R.color.neutral_300))

        }

        Glide.with(this)
            .load(userLogin.avatar)
            .placeholder(R.drawable.arjunadummy2)
            .centerCrop()
            .into(binding.imgProfile);

        binding.btnUbah.setOnClickListener {

        }

        binding.btnLangganan.setOnClickListener {
            val intent = Intent(activity, LanggananActivity::class.java)
            startActivity(intent)
        }

        binding.btnBantuan.setOnClickListener {
            val intent = Intent(activity, BantuanActivity::class.java)
            startActivity(intent)
        }

        binding.btnLaporkan.setOnClickListener {
            val intent = Intent(activity, LaporkanMasalahActivity::class.java)
            startActivity(intent)
        }

        binding.btnTentang.setOnClickListener {
            val intent = Intent(activity, TentangAplikasiActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            showDialogLogout(requireContext())
        }
    }

    private fun showDialogLogout(context: Context) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_information, null)


        val imgInformation: ImageView = dialogView.findViewById(R.id.img_information)
        val textTitle: TextView = dialogView.findViewById(R.id.tv_information_title)
        val textDesc: TextView = dialogView.findViewById(R.id.tv_information_description)
        val buttonInformation: Button = dialogView.findViewById(R.id.btn_information)

        imgInformation.setImageResource(R.drawable.img_logo_danger_information)
        textTitle.text = "Pesan Informasi"
        textDesc.text = "Apakah anda yakin ingin keluar dari aplikasi baksara?"
        buttonInformation.text = "Keluar"
        buttonInformation.background.setTint(resources.getColor(R.color.danger))

        builder.setView(dialogView)
        val dialog: AlertDialog = builder.create()
        buttonInformation.setOnClickListener {
            deleteUser()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        dialog.show()
    }

    fun getUser(): User {
        val name = userPref.getString(MainActivity.FULLNAME,"")
        val email = userPref.getString(MainActivity.EMAIL,"")
        val avatar = userPref.getString(MainActivity.AVATAR,"")
        val id = userPref.getInt(MainActivity.UNIQUEID,0)
        val exp = userPref.getInt(MainActivity.EXP,0)
        val level = userPref.getInt(MainActivity.LEVEL,0)
        val limit = userPref.getInt(MainActivity.CURRENTLIMIT,0)
        val kelas = userPref.getInt(MainActivity.KELAS,0)
        val modul = userPref.getInt(MainActivity.MODUL,0)
        val token = userPref.getString(MainActivity.TOKEN,"")
        val langganan = userPref.getInt(MainActivity.PREMIUM,1)
        val _langgananObject = Langganan(langganan,"",0.0f,0)
        var listOfRiwayat = mutableListOf<RiwayatBelajar>()
        val _riwayatBelajarObject = RiwayatBelajar(0,id,modul,kelas)
        listOfRiwayat.add(_riwayatBelajarObject)
        return User(id,_langgananObject,name,email,token,avatar, exp,level,limit,kadaluarsa = null,null,listOfRiwayat)
    }

    fun deleteUser(){
        val editor = userPref.edit()
        editor.remove(MainActivity.FULLNAME)
        editor.remove(MainActivity.EMAIL)
        editor.remove(MainActivity.AVATAR)
        editor.remove(MainActivity.UNIQUEID)
        editor.remove(MainActivity.EXP)
        editor.remove(MainActivity.LEVEL)
        editor.remove(MainActivity.CURRENTLIMIT)
        editor.remove(MainActivity.PREMIUM)
        editor.remove(MainActivity.KELAS)
        editor.remove(MainActivity.MODUL)
        editor.remove(MainActivity.TOKEN)
        editor.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfilFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}