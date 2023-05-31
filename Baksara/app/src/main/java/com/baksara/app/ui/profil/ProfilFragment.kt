package com.baksara.app.ui.profil

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.baksara.app.R
import com.baksara.app.databinding.FragmentProfilBinding
import com.baksara.app.ui.LoginActivity
import com.baksara.app.ui.profil.bantuan.BantuanActivity
import com.baksara.app.ui.profil.langganan.LanggananActivity
import com.baksara.app.ui.profil.laporkanmasalah.LaporkanMasalahActivity
import com.baksara.app.ui.profil.pencapaian.PencapaianActivity
import com.baksara.app.ui.profil.tentangaplikasi.TentangAplikasiActivity
import com.baksara.app.ui.tantangan.hasil.BerhasilTantanganActivity

class ProfilFragment : Fragment() {
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
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
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        dialog.show()
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