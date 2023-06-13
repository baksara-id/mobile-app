package com.baksara.app.ui.scanner

import android.app.AlertDialog
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.ActivityScannerBinding
import com.baksara.app.response.Langganan
import com.baksara.app.response.RiwayatBelajar
import com.baksara.app.response.User
import com.baksara.app.ui.MainActivity
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.Locale

class ScannerActivity : AppCompatActivity() {
    private var _binding: ActivityScannerBinding? = null
    private val binding get() = _binding!!
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private lateinit var scannerViewModel: ScannerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userPref = getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        scannerViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@ScannerActivity))[ScannerViewModel::class.java]

        var langganan = if(userPref.getInt(MainActivity.PREMIUM, 0) == 0) false else true

        binding.captureImage.setOnClickListener {
            if(userPref.getBoolean(MainActivity.LIMITREACH, false) && !langganan){
                // Kalau udah LimitReach dan Tidak Langganan
                showMaxLimitScanDialog(this)
            }
            else{
                takePhoto()
            }
        }

        binding.btnGallery.setOnClickListener {
            if(userPref.getBoolean(MainActivity.LIMITREACH, false) && !langganan){
                // Kalau udah LimitReach dan Tidak Langganan
                showMaxLimitScanDialog(this)
            }
            else{
                startGallery()
            }

        }

        binding.btnScantips.setOnClickListener {
            showScanTipsDialog(this)
        }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUI()
        startCamera()
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (exc: Exception) {
                Toast.makeText(
                    this@ScannerActivity,
                    "Gagal memunculkan kamera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun startGallery(){
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@ScannerActivity)
                val intent = Intent(this@ScannerActivity, TransliterasiActivity::class.java)
                // Kirim ke Transliterasi Activity
                // Kirim Gambarnya ke Network
                scannerViewModel.fetchScannerResponse(myFile)
                scannerViewModel.liveDataResponseScanner.observe(this@ScannerActivity){ result->
                    result.onSuccess {
                        val resultScanner = it.result
                        intent.putExtra(TransliterasiActivity.HASIL, resultScanner)
                    }
                    result.onFailure {
                        val status = "gagal"
                        val resultFail = "Gagal terdapat kesalahan pada sistem"
                        intent.putExtra(TransliterasiActivity.STATUS, status)
                        intent.putExtra(TransliterasiActivity.HASIL, resultFail)
                    }
                    startActivity(intent)
                }
            }
        }
    }

    fun uriToFile(selectedImg: Uri, context: Context): File {
        val contentResolver: ContentResolver = context.contentResolver
        val myFile = createFile(application)

        val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
        val outputStream: OutputStream = FileOutputStream(myFile)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()

        return myFile
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = createFile(application)
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Toast.makeText(
                        this@ScannerActivity,
                        "Gagal mengambil gambar.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    Toast.makeText(
                        this@ScannerActivity,
                        "Berhasil mengambil gambar.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Kirim Gambarnya ke Network
                    val intent = Intent(this@ScannerActivity, TransliterasiActivity::class.java)
                    startActivity(intent)
                }
            }
        )
    }

    fun createFile(application: Application): File {
        val FILENAME_FORMAT = "dd-MMM-yyyy"

        val timeStamp: String = SimpleDateFormat(
            FILENAME_FORMAT,
            Locale.US
        ).format(System.currentTimeMillis())

        val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
            File(it, application.resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        val outputDirectory = if (
            mediaDir != null && mediaDir.exists()
        ) mediaDir else application.filesDir

        return File(outputDirectory, "$timeStamp.jpg")
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun showMaxLimitScanDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_information, null)


        val imgInformation: ImageView = dialogView.findViewById(R.id.img_information)
        val textTitle: TextView = dialogView.findViewById(R.id.tv_information_title)
        val textDesc: TextView = dialogView.findViewById(R.id.tv_information_description)
        val buttonInformation: Button = dialogView.findViewById(R.id.btn_information)

        imgInformation.setImageResource(R.drawable.img_logo_information)
        textTitle.text = "Pesan Informasi"

        textDesc.text = "Batas scanning anda sudah habis. Lakukan pembelian langganan untuk melakukan scanning."
        buttonInformation.text = "Mengerti"

        builder.setView(dialogView)
        val dialog: AlertDialog = builder.create()
        buttonInformation.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showScanTipsDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_tips, null)

        val buttonMengerti: Button = dialogView.findViewById(R.id.btn_tips_mengerti)
        val imageTipsBenar: ImageView = dialogView.findViewById(R.id.img_tips_benar)
        val imageTipsSalah: ImageView = dialogView.findViewById(R.id.img_tips_salah1)
        val imageTipsSalah2: ImageView = dialogView.findViewById(R.id.img_tips_salah2)
        val imageTipsSalah3: ImageView = dialogView.findViewById(R.id.img_tips_salah3)
        val imageTipsSalah4: ImageView = dialogView.findViewById(R.id.img_tips_salah4)

        imageTipsBenar.setImageResource(R.drawable.tipsbenar)
        imageTipsSalah.setImageResource(R.drawable.tips_salah1)
        imageTipsSalah2.setImageResource(R.drawable.tips_salah2)
        imageTipsSalah3.setImageResource(R.drawable.tips_salah3)
        imageTipsSalah4.setImageResource(R.drawable.tips_salah4)

        builder.setView(dialogView)
        val dialog: AlertDialog = builder.create()
        buttonMengerti.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}