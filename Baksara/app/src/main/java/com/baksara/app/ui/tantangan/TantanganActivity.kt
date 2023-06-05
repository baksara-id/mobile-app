package com.baksara.app.ui.tantangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.adapter.ListTantanganWideAdapter
import com.baksara.app.databinding.ActivityTantanganBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.ui.LoginActivity

class TantanganActivity : AppCompatActivity() {

    private var _binding: ActivityTantanganBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTantanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tantangan"

        setupTantanganAdapter()

        binding.btnTantanganSelengkapnya3.setOnClickListener {
            val intent = Intent(this, RiwayatTantanganActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupTantanganAdapter(){
        val listTantangan = InitialDataSource.getTantangans()

        val layoutManager = LinearLayoutManager(this)
        binding.rvTantanganWide.layoutManager = layoutManager

        val adapter = ListTantanganWideAdapter(listTantangan)
        binding.rvTantanganWide.adapter = adapter
        binding.inputTantanganSearch.addTextChangedListener (object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                val filteredList = listTantangan.filter {
                    it.nama.contains(query)
                }
                adapter.setListTantangan(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }

}