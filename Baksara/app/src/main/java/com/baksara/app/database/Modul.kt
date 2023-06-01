package com.baksara.app.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//@Parcelize
//@Entity(tableName = "moduls")
//data class Modul(
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "id")
//    val id: Int,
//    @ColumnInfo(name = "nomor")
//    val nomor: String,
//    @ColumnInfo(name = "deskripsi")
//    val deskripsi: String,
//    @ColumnInfo(name = "url_background")
//    val url_background: String,
//    @ColumnInfo(name = "terkunci")
//    val terkunci: Boolean,
//    @ColumnInfo(name = "selesai")
//    val selesai: Boolean,
//) : Parcelable