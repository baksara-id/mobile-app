package com.baksara.app.ui.soal.Hasil

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.FragmentBerhasilBinding
import com.baksara.app.response.Lencana
import com.baksara.app.response.User
import com.baksara.app.ui.MainActivity
import com.baksara.app.ui.soal.baca.BacaFragment
import com.baksara.app.ui.soal.pilihan.PilihanFragment
import com.baksara.app.ui.tantangan.hasil.BerhasilTantanganActivity
import com.bumptech.glide.Glide


class BerhasilFragment : Fragment() {

    private var _binding: FragmentBerhasilBinding? = null
    private val binding get() = _binding!!
    private lateinit var fadeInAnimator: ObjectAnimator
    private lateinit var berhasilViewModel: BerhasilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBerhasilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())

        val pelajaranId = arguments?.getInt(PilihanFragment.PELAJARAN_ID) ?: 0
        val modulId = arguments?.getInt(PilihanFragment.MODUL_ID) ?: 0
        val userPref = requireActivity().getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        val userId = userPref.getInt(MainActivity.UNIQUEID, -1)
        val pelajaranIdNext = if(pelajaranId != 4) pelajaranId + 1 else 1
        val modulIdNext = if(modulId != 3) modulId + 1 else 3

        berhasilViewModel = ViewModelProvider(this, viewModelFactory)[BerhasilViewModel::class.java]

        //Unlock next pelajaran
        berhasilViewModel.setPelajaranSelesai(true, pelajaranId)
        berhasilViewModel.setPelajaranTerkunci(false, pelajaranId + 1)

        // Update Riwayat Belajar Saat Ini Khusus Pelajaran/Kelas
        berhasilViewModel.fetchRiwayatBelajarResponse(userId,pelajaranIdNext,modulId)
        berhasilViewModel.liveDataUpdateRiwayatResponse.observe(requireActivity()){ response->
            response.onSuccess {
                val nomorModulBaru = it.data?.riwayatBelajar?.nomor_modul ?: 1
                val nomorPelajranBaru = it.data?.riwayatBelajar?.nomor_pelajaran ?: 1
                setModulPelajaranBaru(nomorModulBaru, nomorPelajranBaru, userPref)
                Toast.makeText(requireContext(), "Anda berhasil menyelesaikan kelas dan memperoleh 400 EXP", Toast.LENGTH_SHORT).show()
            }
            response.onFailure {

            }
        }

        // Update Riwayat belajar Saat Ini Khusus Naik Modul
        if(pelajaranId == 4){
            berhasilViewModel.fetchRiwayatBelajarResponse(userId, pelajaranIdNext, modulIdNext)
            berhasilViewModel.setModulSelesai(true, modulId)
            berhasilViewModel.fetchLencana(userId, modulId)
        }

        berhasilViewModel.liveDataResponseLencana.observe(requireActivity()){response->
            response.onSuccess {
                val lencana = it.data?.lencanaResponse?.lencana ?: Lencana(0,"a","")
                showLencanaGetDialog(requireActivity(),lencana)
            }
            response.onFailure {

            }
        }

        // EXP and Level Calculation
        val expEarned = 400
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
                berhasilViewModel.fetchResponseUpdateUserLevel(counter,it,earn)
            }
        }
        else{
            // Panggil update exp
            userId.let{
                berhasilViewModel.fetchResponseUpdateUserExp(earn, it)
            }
        }

        berhasilViewModel.liveDataResponseUpdateUserEXP.observe(requireActivity()){ response ->
            response.onSuccess {
                val user = it.data?.update
                setUser(user, userPref)
            }
            response.onFailure {
                // Kalau Gagal
            }
        }
        berhasilViewModel.liveDataResponseUpdateUserLevelEXP.observe(requireActivity()){ response ->
            response.onSuccess {
                val user = it.data?.update
                setUser(user, userPref)
            }
            response.onFailure {
                // Kalau Gagal
            }
        }
        binding.tvBerhasilExp.text = "+ 400XP"

        fadeInAnimator = ObjectAnimator.ofFloat(binding.tvBerhasilExp, "alpha", 0f, 1f)
            .apply {
                duration = 2000
                doOnEnd { binding.tvBerhasilExp.visibility = View.VISIBLE }
            }

        fadeInAnimator.start()

        binding.btnKembaliKelas.setOnClickListener {
            activity?.finish()
        }
    }

    fun calculateEXPNeeded(nextLevel: Int): Int //this function is used to calculate next Level EXP needed
    {
        val expNeededNow = nextLevel * nextLevel * 250 + nextLevel * 250
        val currentLevel = nextLevel - 1
        val expCummulative = currentLevel * currentLevel * 250 + currentLevel * 250
        return expNeededNow - expCummulative
    }

    private fun showLencanaGetDialog(context: Context, lencana: Lencana) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_lencana, null)


        val imgLencana: ImageView = dialogView.findViewById(R.id.img_lencana)
        val tvNama: TextView = dialogView.findViewById(R.id.tv_lencana_nama)
        val tvDeskripsi: TextView = dialogView.findViewById(R.id.tv_lencana_description)
        val buttonConfirm: Button = dialogView.findViewById(R.id.btn_konfirmasi)

        Glide.with(context).load(lencana.url_gambar)
            .centerCrop()
            .into(imgLencana)
        imgLencana.setImageResource(R.drawable.img_logo_information)
        tvNama.text = "${lencana.nama}"

        tvDeskripsi.text = "Selamat anda memperoleh Lencana ${lencana.nama}"
        buttonConfirm.text = "Mengerti"

        builder.setView(dialogView)
        val dialog: AlertDialog = builder.create()
        buttonConfirm.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    fun setUser(user: User?, userPref : SharedPreferences){
        val editor = userPref.edit()
        editor.putString(MainActivity.FULLNAME, user?.name)
        editor.putInt(MainActivity.UNIQUEID, user?.id?:0)
        editor.putInt(MainActivity.EXP, user?.exp?:0)
        editor.putInt(MainActivity.LEVEL, user?.level?:0)
        editor.apply()
    }

    fun setModulPelajaranBaru(nomorModulBaru: Int, nomorPelajranBaru: Int, userPref: SharedPreferences){
        val editor = userPref.edit()
        editor.putInt(MainActivity.MODUL, nomorModulBaru)
        editor.putInt(MainActivity.KELAS, nomorPelajranBaru)
        editor.apply()
    }
}