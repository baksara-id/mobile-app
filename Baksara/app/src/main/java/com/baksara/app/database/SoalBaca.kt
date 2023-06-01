package com.baksara.app.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//@Parcelize
//@Entity(tableName = "soalbacas",
//    foreignKeys = [
//        ForeignKey(
//            entity = Pelajaran::class,
//            parentColumns = ["id"],
//            childColumns = ["pelajaranId"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ])
//data class SoalBaca(
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "id")
//    val id: Int,
//    @ColumnInfo(name = "pelajaranId")
//    val pelajaranId: Int,
//    @ColumnInfo(name = "aksara")
//    val aksara: String,
//    @ColumnInfo(name = "latin")
//    val latin: String,
//    @ColumnInfo(name = "urutan")
//    val urutan: Int,
//) : Parcelable