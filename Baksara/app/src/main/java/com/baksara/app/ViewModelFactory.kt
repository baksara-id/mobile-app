package com.baksara.app

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.ui.MainViewModel
import com.baksara.app.ui.artikel.ArtikelViewModel
import com.baksara.app.ui.home.HomeViewModel
import com.baksara.app.ui.kelas.KelasViewModel
import com.baksara.app.ui.profil.ProfileViewModel
import com.baksara.app.ui.pustaka.ContohKamusViewModel
import com.baksara.app.ui.pustaka.DetailKamusViewModel
import com.baksara.app.ui.pustaka.KamusViewModel
import com.baksara.app.ui.pustaka.PustakaViewModel
import com.baksara.app.ui.scanner.ScannerViewModel
import com.baksara.app.ui.soal.baca.BacaViewModel
import com.baksara.app.ui.soal.gambar.GambarViewModel
import com.baksara.app.ui.soal.pilihan.PilihanViewModel
import com.baksara.app.ui.tantangan.TantanganViewModel

class ViewModelFactory private constructor(
    private val repository: BaksaraRepository
    ) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }

            modelClass.isAssignableFrom(KelasViewModel::class.java) -> {
                KelasViewModel(repository) as T
            }

            modelClass.isAssignableFrom(BacaViewModel::class.java) -> {
                BacaViewModel(repository) as T
            }

            modelClass.isAssignableFrom(GambarViewModel::class.java) -> {
                GambarViewModel(repository) as T
            }

            modelClass.isAssignableFrom(PilihanViewModel::class.java) -> {
                PilihanViewModel(repository) as T
            }

            modelClass.isAssignableFrom(KamusViewModel::class.java) -> {
                KamusViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ContohKamusViewModel::class.java) -> {
                ContohKamusViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailKamusViewModel::class.java) -> {
                DetailKamusViewModel(repository) as T
            }

            modelClass.isAssignableFrom(PustakaViewModel::class.java) -> {
                PustakaViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ArtikelViewModel::class.java) -> {
                ArtikelViewModel(repository) as T
            }

            modelClass.isAssignableFrom(TantanganViewModel::class.java) -> {
                TantanganViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ScannerViewModel::class.java) -> {
                ScannerViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }
}