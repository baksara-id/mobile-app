package com.baksara.app.database

import androidx.room.*

@Entity
data class Modul(
    @PrimaryKey
    val id: Int,
    val nomor: String,
    val deskripsi: String,
    val url_background: String,
    val terkunci: Boolean,
    val selesai: Boolean,
)

@Entity
data class Pelajaran(
    @PrimaryKey
    val id: Int,
    val modulId: Int,
    val nomor: String,
    val deskripsiAksara: String,
    val deskripsiLatin: String,
    val terkunci: Boolean,
    val selesai: Boolean,
)

@Entity
data class SoalBaca(
    @PrimaryKey
    val id: Int,
    val pelajaranId: Int,
    val aksara: String,
    val latin: String,
    val urutan: Int,
)

@Entity
data class SoalGambar(
    @PrimaryKey
    val id: Int,
    val pelajaranId: Int,
    val latin: String,
    val urutan: Int,
)

@Entity
data class SoalPilihan(
    @PrimaryKey
    val id: Int,
    val pelajaranId: Int,
    val aksara: String,
    val pilihanSatu: String,
    val pilihanDua: String,
    val pilihanTiga: String,
    val pilihanEmpat: String,
    val jawabanBenar: String,
    val urutan: Int,
)

data class ModulAndPelajaran(
    @Embedded
    val modul: Modul,
    @Relation(
        parentColumn = "id",
        entityColumn = "modulId"
    )
    val pelajaran: List<Pelajaran>
)

data class PelajaranAndSoalBaca(
    @Embedded
    val pelajaran: Pelajaran,
    @Relation(
        parentColumn = "id",
        entityColumn = "pelajaranId"
    )
    val soalBaca: List<SoalBaca>
)

data class PelajaranAndSoalGambar(
    @Embedded
    val pelajaran: Pelajaran,
    @Relation(
        parentColumn = "id",
        entityColumn = "pelajaranId"
    )
    val soalGambar: List<SoalGambar>
)

data class PelajaranAndSoalPilihan(
    @Embedded
    val pelajaran: Pelajaran,
    @Relation(
        parentColumn = "id",
        entityColumn = "pelajaranId"
    )
    val soalPilihan : List<SoalPilihan>
)

@Entity
data class Kamus(
    @PrimaryKey
    val id: Int,
    val aksara: String,
    val latin: String,
)

@Entity
data class Penggunaan(
    @PrimaryKey
    val id: Int,
    val kamusId: Int,
    val aksara: String,
    val latin: String,
)

data class KamusAndPenggunaan(
    @Embedded
    val pelajaran: Pelajaran,
    @Relation(
        parentColumn = "id",
        entityColumn = "kamusId"
    )
    val penggunaan : List<Penggunaan>
)

data class Tantangan(
    val id:Int,
    val nama: String,
    val jumlahExp: Int,
)
