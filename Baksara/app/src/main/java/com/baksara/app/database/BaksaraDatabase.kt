package com.baksara.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Modul::class, Pelajaran::class, SoalBaca::class, SoalGambar::class, SoalPilihan::class, Penggunaan::class, Kamus::class],
    version = 1
)
abstract class BaksaraDatabase: RoomDatabase() {

    abstract fun baksaraDao(): BaksaraDao

//    companion object {
//        @Volatile
//        private var INSTANCE: KelasRoomDatabase? = null
//
//        fun getDatabase(context: Context): KelasRoomDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    KelasRoomDatabase::class.java,
//                    "kelas_db"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}