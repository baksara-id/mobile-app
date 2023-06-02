package com.baksara.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(
//    entities = [Modul::class, Pelajaran::class, SoalBaca::class, SoalGambar::class, SoalPilihan::class, Penggunaan::class, Kamus::class],
//    version = 1
//)
//abstract class KelasRoomDatabase:RoomDatabase() {
//    abstract fun modulDao(): ModulDao
//    abstract fun pelajaranDao(): PelajaranDao
//    abstract fun soalBacaDao(): SoalBacaDao
//    abstract fun soalGambarDao(): SoalGambarDao
//    abstract fun soalPilihanDao(): SoalPilihanDao
//    abstract fun penggunaanDao(): PenggunaanDao
//    abstract fun kamusDao(): KamusDao
//
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
//}