package com.baksara.app.helper

import com.baksara.app.database.*

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

    fun getTantangans(): List<Tantangan>{
        return listOf(
            Tantangan(1, "Tantangan 1", 150, "ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat", "https://upload.wikimedia.org/wikipedia/commons/3/33/Kraton_Yogyakarta_Pagelaran.jpg"),
            Tantangan(2, "Tantangan 2", 150, "ꦮꦼꦢꦁꦧꦸꦧꦸꦏ꧀ꦠꦤ꧀ꦥꦒꦸꦭ", "Wedang Bubuk Tanpa Gula", "https://akcdn.detik.net.id/community/media/visual/2021/06/23/resep-wedang-jahe-susu_43.jpeg?w=700&q=90"),
            Tantangan(3, "Tantangan 3", 150, "ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu", "https://www.astronauts.id/blog/wp-content/uploads/2022/08/Makanan-Khas-Daerah-tiap-Provinsi-di-Indonesia-Serta-Daerah-Asalnya.jpg"),
        )
    }

    fun getCeritas(): List<Cerita>{
        return listOf(
            Cerita(1, "Arjuna 1","Deskripsi Arjuna","https://png.pngtree.com/png-clipart/20221125/ourlarge/pngtree-javanese-arjuna-wayang-png-image_6479984.png"),
            Cerita(2, "Arjuna 2","Deskripsi Arjuna","https://png.pngtree.com/png-clipart/20221125/ourlarge/pngtree-javanese-arjuna-wayang-png-image_6479984.png"),
            Cerita(3, "Arjuna 3","Deskripsi Arjuna","https://png.pngtree.com/png-clipart/20221125/ourlarge/pngtree-javanese-arjuna-wayang-png-image_6479984.png"),
            Cerita(4, "Arjuna 4","Deskripsi Arjuna","https://png.pngtree.com/png-clipart/20221125/ourlarge/pngtree-javanese-arjuna-wayang-png-image_6479984.png"),
            Cerita(5, "Arjuna 5","Deskripsi Arjuna","https://png.pngtree.com/png-clipart/20221125/ourlarge/pngtree-javanese-arjuna-wayang-png-image_6479984.png"),
            Cerita(6, "Arjuna 6","Deskripsi Arjuna","https://png.pngtree.com/png-clipart/20221125/ourlarge/pngtree-javanese-arjuna-wayang-png-image_6479984.png"),
            Cerita(7, "Arjuna 7","Deskripsi Arjuna","https://png.pngtree.com/png-clipart/20221125/ourlarge/pngtree-javanese-arjuna-wayang-png-image_6479984.png"),
        )
    }

    fun getListKamus(): List<KamusBelajar>{
        return listOf(
            KamusBelajar(1,"20 Aksara","ꦲ", "Ha", "Carakan"),
            KamusBelajar(2,"8 Aksara","ꦲ", "Ha","Sandhangan"),
            KamusBelajar(3,"20 Aksara","ꦲ", "Ha","Pasangan"),
        )
    }

    fun getAksaraKamus(): List<Kamus>{
        return listOf(
            Kamus(1,"ꦲ", "Ha"),
            Kamus(2, "ꦤ", "Na"),
            Kamus(3, "ꦕ", "Ca"),
            Kamus(4, "ꦫ", "Ra"),
            Kamus(5, "ꦏ", "Ka"),

            Kamus(6, "ꦣ", "Da"),
            Kamus(7, "ꦠ", "Ta"),
            Kamus(8, "ꦱ", "Sa"),
            Kamus(9, "ꦮ", "Wa"),
            Kamus(10, "ꦭ", "La"),

            Kamus(11, "ꦥ", "Pa"),
            Kamus(12, "ꦣ", "Dha"),
            Kamus(13, "ꦗ", "Ja"),
            Kamus(14, "ꦪ", "Ya"),
            Kamus(15, "ꦚ", "Nya"),

            Kamus(16, "ꦩ", "Ma"),
            Kamus(17, "ꦒ", "Ga"),
            Kamus(18, "ꦧ", "Ba"),
            Kamus(19, "ꦠ", "Ta"),
            Kamus(20, "ꦔ", "Nga"),

            Kamus(21,"꧀ꦲ", "Ha"),
            Kamus(22, "꧀ꦤ", "Na"),
            Kamus(23, "꧀ꦕ", "Ca"),
            Kamus(24, "꧀ꦫ", "Ra"),
            Kamus(25, "꧀ꦏ", "Ka"),

            Kamus(26, "꧀ꦢ", "Da"),
            Kamus(27, "꧀ꦠ", "Ta"),
            Kamus(28, "꧀ꦱ", "Sa"),
            Kamus(29, "꧀ꦮ", "Wa"),
            Kamus(30, "꧀ꦭ", "La"),

            Kamus(31, "꧀ꦥ", "Pa"),
            Kamus(32, "꧀ꦣ", "Dha"),
            Kamus(33, "꧀ꦗ", "Ja"),
            Kamus(34, "꧀ꦪ", "Ya"),
            Kamus(35, "꧀ꦚ", "Nya"),

            Kamus(36, "꧀ꦩ", "Ma"),
            Kamus(37, "꧀ꦒ", "Ga"),
            Kamus(38, "꧀ꦧ", "Ba"),
            Kamus(39, "꧀ꦠ", "Ta"),
            Kamus(40, "꧀ꦔ", "Nga"),

            Kamus(41, "ꦶ", "i"),
            Kamus(42, "ꦺ", "è"),
            Kamus(43, "ꦺꦴ", "o"),
            Kamus(44, "ꦸ", "u"),
            Kamus(45, "ꦼ", "e"),
            Kamus(46, "ꦁ", "-ng"),
            Kamus(47, "ꦂ", "-r"),
            Kamus(48, "ꦃ", "-h"),
        )
    }

    fun getPenggunaanKamus(): List<Penggunaan>{
        return listOf(
            Penggunaan(1,1,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(2,1,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(3,2,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(4,2,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(5,3,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(6,3,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(7,4,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(8,4,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(9,5,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(10,5,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(11,6,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(12,6,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(13,7,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(14,7,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(15,8,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(16,8,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(17,9,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(18,9,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(19,10,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(20,10,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(21,11,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(22,11,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(23,12,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(24,12,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(25,13,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(26,13,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(27,14,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(28,14,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(29,15,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(30,15,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(31,16,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(32,16,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(33,17,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(34,17,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(35,18,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(36,18,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(37,19,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(38,19,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
            Penggunaan(39,20,"ꦱꦶꦤꦲꦸꦠꦼꦩ꧀ꦧꦁꦩꦕꦥꦠ꧀", "Keraton Yogyakarta Hadiningrat"),
            Penggunaan(40,20,"ꦩꦔꦤ꧀ꦱꦼꦒꦠꦺꦩ꧀ꦥꦺꦏꦫꦺꦴꦠꦲꦸ", "Mangan Sega Tempe Karo Tahu"),
        )
    }

    fun getArtikels(): List<Artikel>{
        return listOf(
            Artikel(1, "Jokowi mantap", "Cihuy","https://test"),
            Artikel(2, "Jokowi mantap", "Cihuy","https://test"),
            Artikel(3, "Jokowi mantap", "Cihuy","https://test"),
            Artikel(4, "Jokowi mantap", "Cihuy","https://test"),
            )
    }

}