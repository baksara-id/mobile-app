package com.baksara.app.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//@Parcelize
//@Entity(tableName = "pelajarans",
//    foreignKeys = [ ForeignKey(
//            entity = Modul::class,
//            parentColumns = ["id"],
//            childColumns = ["modulId"],
//            onDelete = ForeignKey.CASCADE
//        )])
//data class Pelajaran(
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "id")
//    val id: Int,
//    @ColumnInfo(name = "modulId")
//    val modulId: Int,
//    @ColumnInfo(name = "nomor")
//    val nomor: String,
//    @ColumnInfo(name = "deskripsiAksara")
//    val deskripsiAksara: String,
//    @ColumnInfo(name = "deskripsiLatin")
//    val deskripsiLatin: String,
//    @ColumnInfo(name = "terkunci")
//    val terkunci: Boolean,
//    @ColumnInfo(name = "selesai")
//    val selesai: Boolean,
//) : Parcelable
