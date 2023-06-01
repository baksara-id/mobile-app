package com.baksara.app.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//@Parcelize
//@Entity(tableName = "soalpilihans",
//    foreignKeys = [
//        ForeignKey(
//            entity = Pelajaran::class,
//            parentColumns = ["id"],
//            childColumns = ["pelajaranId"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ])
//data class SoalPilihan(
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "id")
//    val id: Int,
//    @ColumnInfo(name = "pelajaranId")
//    val pelajaranId: Int,
//    @ColumnInfo(name = "aksara")
//    val aksara: String,
//    @ColumnInfo(name = "pilihanSatu")
//    val pilihanSatu: String,
//    @ColumnInfo(name = "pilihanDua")
//    val pilihanDua: String,
//    @ColumnInfo(name = "pilihanTiga")
//    val pilihanTiga: String,
//    @ColumnInfo(name = "pilihanEmpat")
//    val pilihanEmpat: String,
//    @ColumnInfo(name = "jawabanBenar")
//    val jawabanBenar: String,
//    @ColumnInfo(name = "urutan")
//    val urutan: Int,
//): Parcelable