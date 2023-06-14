package com.baksara.app.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.adapter.ListInAppBannerAdapter
import com.baksara.app.adapter.ListModulAdapter
import com.baksara.app.adapter.ListTantanganAdapter
import com.baksara.app.database.InAppBanner
import com.baksara.app.database.Modul
import com.baksara.app.databinding.FragmentHomeBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.response.Langganan
import com.baksara.app.response.RiwayatBelajar
import com.baksara.app.response.Tantangan
import com.baksara.app.response.User
import com.baksara.app.ui.MainActivity
import com.baksara.app.ui.tantangan.TantanganActivity


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var userPref: SharedPreferences
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPref = requireActivity().getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]


        homeViewModel.liveDataTantangan.observe(requireActivity()){ result ->
            result.onSuccess { tantangans->
                val listTantanganBelum = tantangans.data?.tantanganBelum ?: emptyList()
                if(listTantanganBelum.isEmpty()){
                    binding.rvTantangan.visibility = View.GONE
                    binding.tvNodata.visibility = View.VISIBLE
                }
                else{
                    binding.rvTantangan.visibility = View.VISIBLE
                    binding.tvNodata.visibility = View.GONE
                }
                setupTantanganAdapter(listTantanganBelum)
            }

            result.onFailure {
                //Kalau gagal
            }
        }

        binding.btnTantanganSelengkapnya.setOnClickListener {
            val intent = Intent(activity, TantanganActivity::class.java)
            startActivity(intent)
        }

        homeViewModel.liveDataIsLoading.observe(requireActivity()){
            showLoading(it)
        }

        setupInAppBannerAdapter(InitialDataSource.getInAppBanners())
    }

    override fun onResume() {
        super.onResume()
        // Loading Animation Setup
        setupTantanganAdapter(emptyList())
        binding.rvTantangan.visibility = View.VISIBLE
        binding.tvNodata.visibility = View.GONE

        val userLogin = getUser(userPref)
        userLogin.let{
            val userId = it.id?:-1
            val nomorModul = it.riwayatBelajar?.get(0)?.nomor_modul ?:-1
            val nomorPelajaran = it.riwayatBelajar?.get(0)?.nomor_pelajaran ?:-1

            //ambil langganan selain 1 (user biasa)
            if(it.langganan?.id != 1){
                homeViewModel.syncModulTerkunci(false)
            }

            homeViewModel.fetchAllTantanganUser(userId)
            homeViewModel.syncModulSelesai(true, nomorModul)
            homeViewModel.syncPelajaranSelesai(true, nomorPelajaran, nomorModul)
            homeViewModel.syncPelajaranTerkunci(false, nomorPelajaran, nomorModul)
        }
        setHomePage(userLogin)

        homeViewModel.getAllModul().observe(requireActivity()){ moduls ->
            if(moduls.isNotEmpty()){
                setupModulAdapter(moduls)
            }else{

            }
        }

    }

    private fun setupTantanganAdapter(listTantangan: List<Tantangan>){
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTantangan.layoutManager = layoutManager

        val adapter = ListTantanganAdapter(listTantangan)
        binding.rvTantangan.adapter = adapter
    }

    private fun setupInAppBannerAdapter(listInAppBanner: List<InAppBanner>){
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvInappBanner.layoutManager = layoutManager

        val adapter = ListInAppBannerAdapter(listInAppBanner)
        binding.rvInappBanner.adapter = adapter
    }

    private fun setupModulAdapter(moduls: List<Modul>){
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvModul.layoutManager = layoutManager
        val adapter = ListModulAdapter(moduls)
        binding.rvModul.adapter = adapter
    }

    fun getUser(userPref: SharedPreferences): User {
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

    fun setHomePage(user:User){
        binding.tvCurrentLevel.text = user.level.toString()
        val currentEXP = user.exp ?: 0
        binding.tvCurrentAccountExp.text = currentEXP.toString()
        val maxEXP = user.level?.times(500) ?: 500
        binding.tvMaxAccountExp.text = maxEXP.toString()
        binding.expBar.max = maxEXP
        binding.expBar.progress = currentEXP
    }

    fun showLoading(isLoading: Boolean){
        binding.loadingTantanganHome.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}