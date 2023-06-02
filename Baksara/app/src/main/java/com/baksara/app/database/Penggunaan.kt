package com.baksara.app.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


//@Parcelize
//@Entity(tableName = "penggunaans",
//    foreignKeys = [ ForeignKey(
//            entity = Modul::class,
//            parentColumns = ["id"],
//            childColumns = ["kamusId"],
//            onDelete = ForeignKey.CASCADE
//        )])
//data class Penggunaan(
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "id")
//    val id: Int,
//    @ColumnInfo(name = "kamusId")
//    val kamusId: Int,
//    @ColumnInfo(name = "aksara")
//    val aksara: String,
//    @ColumnInfo(name = "latin")
//    val latin: String,
//) : Parcelable
