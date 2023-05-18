package com.baksara.app.helper

import com.baksara.app.database.Modul
import com.baksara.app.database.Pelajaran
import com.baksara.app.database.SoalBaca
import com.baksara.app.database.SoalGambar
import com.baksara.app.database.SoalPilihan

object InitialDataSource {
    fun getModuls(): List<Modul>{
        return listOf(
            Modul(1,"1", "Carakan", "ꦲ", false, false),
            Modul(2,"2", "Sandhangan Swara", "ꦴ", true, false),
            Modul(3,"3", "Pasangan", "꧀ꦲ", true, false)
        )
    }

    fun getPelajaran(): List<Pelajaran>{
        return listOf(
            Pelajaran(1,1, "1", "ꦲ ꦤ ꦕ ꦫ ꦏ", "Ha   Na   Ca   Ra   Ka",false, false),
            Pelajaran(2,1, "2", "ꦣ ꦠ ꦱ ꦮ ꦭ", "Da   Ta   Sa   Wa   La",true, false),
            Pelajaran(3,1, "3", "ꦥ ꦣ ꦗ ꦪ ꦚ", "Pa  Dha   Ja   Ya   Nya",true, false),
            Pelajaran(4,1, "4", "ꦩ ꦒ ꦧ ꦠ ꦔ", "Ma   Ga   Ba   Ta   Nga",true, false),
        )
    }

    fun getSoalBaca(): List<SoalBaca>{
        return listOf(
            SoalBaca(1,1, "ꦲ", "Ha", 1),
            SoalBaca(2,1, "ꦤ", "Na", 2),
            SoalBaca(3,1, "ꦕ", "Ca", 3),
            SoalBaca(4,1, "ꦫ", "Ra", 4),
            SoalBaca(5,1, "ꦏ", "Ka", 5),

            SoalBaca(6,2, "ꦣ", "Da", 1),
            SoalBaca(7,2, "ꦠ", "Ta", 2),
            SoalBaca(8,2, "ꦱ", "Sa", 3),
            SoalBaca(9,2, "ꦮ", "Wa", 4),
            SoalBaca(10,2, "ꦭ", "La", 5),

            SoalBaca(11,3, "ꦥ", "Pa", 1),
            SoalBaca(12,3, "ꦣ", "Dha", 2),
            SoalBaca(13,3, "ꦗ", "Ja", 3),
            SoalBaca(14,3, "ꦪ", "Ya", 4),
            SoalBaca(15,3, "ꦚ", "Nya", 5),

            SoalBaca(16,4, "ꦩ", "Ma", 1),
            SoalBaca(17,4, "ꦒ", "Ga", 2),
            SoalBaca(18,4, "ꦧ", "Ba", 3),
            SoalBaca(19,4, "ꦠ", "Ta", 4),
            SoalBaca(20,4, "ꦔ", "Nga", 5),
        )
    }

    fun getSoalGambar(): List<SoalGambar>{
        return listOf(
            SoalGambar(1,1, "Ha", 1),
            SoalGambar(2,1, "Na", 2),
            SoalGambar(3,1, "Ca", 3),
            SoalGambar(4,1, "Ra", 4),
            SoalGambar(5,1, "Ka", 5),

            SoalGambar(6,2, "Da", 1),
            SoalGambar(7,2, "Ta", 2),
            SoalGambar(8,2,"Sa", 3),
            SoalGambar(9,2,"Wa", 4),
            SoalGambar(10,2,"La", 5),

            SoalGambar(11,3, "Pa", 1),
            SoalGambar(12,3, "Dha", 2),
            SoalGambar(13,3,"Ja", 3),
            SoalGambar(14,3,"Ya", 4),
            SoalGambar(15,3,"Nya", 5),

            SoalGambar(16,4, "Ma", 1),
            SoalGambar(17,4, "Ga", 2),
            SoalGambar(18,4, "Ba", 3),
            SoalGambar(19,4, "Ta", 4),
            SoalGambar(20,4, "Nga", 5),
        )
    }
    fun getSoalPilihan(): List<SoalPilihan>{
        return listOf(
            SoalPilihan(1,1, "ꦲ", "Ha", "Ca", "Ka", "Ra", "Ha",1),
            SoalPilihan(2,1, "ꦤ", "Ca", "Ka", "Nga", "Na", "Na",2),
            SoalPilihan(3,1, "ꦕ", "Ma", "Ca", "Ka", "Ra", "Ca", 3),
            SoalPilihan(4,1, "ꦫ", "Ka", "Ra", "Ma", "Ha", "Ra", 4),
            SoalPilihan(5,1, "ꦏ", "Na", "Ra", "Ka", "Ma", "Ka", 5),

            SoalPilihan(6,2, "ꦣ", "Sa", "Da", "Ta", "Wa", "Da", 1),
            SoalPilihan(7,2, "ꦠ", "Ta", "Ra", "Ka", "Nga","Ta", 2),
            SoalPilihan(8,2, "ꦱ", "La", "Ga", "Sa", "Da","Sa", 3),
            SoalPilihan(9,2, "ꦮ", "Da", "Wa", "La", "Na","Wa", 4),
            SoalPilihan(10,2, "ꦭ", "Ca", "La", "Na", "Ka","La", 5),

            SoalPilihan(11,3, "ꦥ", "Pa", "Dha", "Ja", "Ya", "Pa", 1),
            SoalPilihan(12,3, "ꦣ", "Ja", "Ma", "Ya", "Dha","Dha", 2),
            SoalPilihan(13,3, "ꦗ", "Na", "Ha", "Ca", "Ja","Ja", 3),
            SoalPilihan(14,3, "ꦪ", "Ga", "Ja", "Ya", "Ma", "Ya", 4),
            SoalPilihan(15,3, "ꦚ", "Nya", "Ja", "Ma", "Ta","Nya", 5),

            SoalPilihan(16,4, "ꦩ", "Ga", "Ma", "Ta", "Nga","Ma", 1),
            SoalPilihan(17,4, "ꦒ", "Ja", "Ma", "Nga", "Ta","Ga", 2),
            SoalPilihan(18,4, "ꦧ", "La", "Ba", "Ja", "Ya","Ba", 3),
            SoalPilihan(19,4, "ꦠ", "Ma", "Na", "Ta", "Ja","Ta", 4),
            SoalPilihan(20,4, "ꦔ", "Ga", "Ma", "Ta", "Nga","Nga", 5),
        )
    }

}