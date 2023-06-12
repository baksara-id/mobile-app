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

    fun getPelajarans(): List<Pelajaran>{
        return listOf(
            Pelajaran(1,1, "1", "ꦲ ꦤ ꦕ ꦫ ꦏ", "Ha   Na   Ca   Ra   Ka",false, false),
            Pelajaran(2,1, "2", "ꦣ ꦠ ꦱ ꦮ ꦭ", "Da   Ta   Sa   Wa   La",true, false),
            Pelajaran(3,1, "3", "ꦥ ꦣ ꦗ ꦪ ꦚ", "Pa  Dha   Ja   Ya   Nya",true, false),
            Pelajaran(4,1, "4", "ꦩ ꦒ ꦧ ꦠ ꦔ", "Ma   Ga   Ba   Ta   Nga",true, false),
        )
    }

    fun getSoalBacas(): List<SoalBaca>{
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

    fun getSoalGambars(): List<SoalGambar>{
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
    fun getSoalPilihans(): List<SoalPilihan>{
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
            KamusBelajar(1,"20 Aksara","ꦲ", "Ha", "Carakan", "Carakan merupakan kata dasar Aksara Jawa yang berjumlah 20 aksara pokok dan bersifat kesukukataan.", false),
            KamusBelajar(2,"8 Aksara","ꦲ", "Ha","Sandhangan", "Sandhangan adalah tanda diakritik yang berfungsi untuk mengubah bunyi pada huruf aksara Jawa.", true),
            KamusBelajar(3,"20 Aksara","ꦲ", "Ha","Pasangan", "Pasangan Aksara Jawa adalah simbol-simbol yang berguna untuk mematikan atau menghilangkan huruf vokal pada aksara dasar Hanacaraka.", true),
        )
    }

    fun getAksaraKamus(): List<Kamus>{
        return listOf(
            Kamus(1, 1,"ꦲ", "Ha"),
            Kamus(2, 1,"ꦤ", "Na"),
            Kamus(3, 1,"ꦕ", "Ca"),
            Kamus(4, 1,"ꦫ", "Ra"),
            Kamus(5, 1,"ꦏ", "Ka"),

            Kamus(6, 1,"ꦣ", "Da"),
            Kamus(7, 1,"ꦠ", "Ta"),
            Kamus(8, 1,"ꦱ", "Sa"),
            Kamus(9, 1,"ꦮ", "Wa"),
            Kamus(10, 1,"ꦭ", "La"),

            Kamus(11, 1,"ꦥ", "Pa"),
            Kamus(12, 1,"ꦣ", "Dha"),
            Kamus(13, 1,"ꦗ", "Ja"),
            Kamus(14, 1,"ꦪ", "Ya"),
            Kamus(15, 1,"ꦚ", "Nya"),

            Kamus(16, 1,"ꦩ", "Ma"),
            Kamus(17, 1,"ꦒ", "Ga"),
            Kamus(18, 1,"ꦧ", "Ba"),
            Kamus(19, 1,"ꦠ", "Ta"),
            Kamus(20, 1,"ꦔ", "Nga"),

            Kamus(21, 3,"꧀ꦲ", "Ha"),
            Kamus(22, 3,"꧀ꦤ", "Na"),
            Kamus(23, 3,"꧀ꦕ", "Ca"),
            Kamus(24, 3,"꧀ꦫ", "Ra"),
            Kamus(25, 3,"꧀ꦏ", "Ka"),

            Kamus(26, 3,"꧀ꦢ", "Da"),
            Kamus(27, 3,"꧀ꦠ", "Ta"),
            Kamus(28, 3,"꧀ꦱ", "Sa"),
            Kamus(29, 3,"꧀ꦮ", "Wa"),
            Kamus(30, 3,"꧀ꦭ", "La"),

            Kamus(31, 3,"꧀ꦥ", "Pa"),
            Kamus(32, 3,"꧀ꦣ", "Dha"),
            Kamus(33, 3,"꧀ꦗ", "Ja"),
            Kamus(34, 3,"꧀ꦪ", "Ya"),
            Kamus(35, 3,"꧀ꦚ", "Nya"),

            Kamus(36, 3,"꧀ꦩ", "Ma"),
            Kamus(37, 3,"꧀ꦒ", "Ga"),
            Kamus(38, 3,"꧀ꦧ", "Ba"),
            Kamus(39, 3,"꧀ꦠ", "Ta"),
            Kamus(40, 3,"꧀ꦔ", "Nga"),

            Kamus(41, 2, "ꦶ", "i"),
            Kamus(42, 2,"ꦺ", "è"),
            Kamus(43, 2,"ꦺꦴ", "o"),
            Kamus(44, 2,"ꦸ", "u"),
            Kamus(45, 2,"ꦼ", "e"),
            Kamus(46, 2,"ꦁ", "-ng"),
            Kamus(47, 2,"ꦂ", "-r"),
            Kamus(48, 2,"ꦃ", "-h"),
        )
    }

    fun getPenggunaanKamus(): List<Penggunaan>{
        return listOf(
            Penggunaan(1,1,"<font color='#3AA04B'>ꦲ</font>ꦏꦸ","Aku"),
            Penggunaan(2,1,"<font color='#3AA04B'>ꦲ</font>ꦗꦶ","Haji"),
            Penggunaan(3,2,"<font color='#3AA04B'>ꦤ</font>ꦠ","Nata"),
            Penggunaan(4,2,"<font color='#3AA04B'>ꦤ</font>ꦫꦶ","Nari"),
            Penggunaan(5,3,"ꦏ <font color='#3AA04B'>ꦕ</font>","Kaca"),
            Penggunaan(6,3,"ꦫ ꦶ<font color='#3AA04B'>ꦕ</font>","Rica"),
            Penggunaan(7,4,"<font color='#3AA04B'>ꦫ</font>ꦱ","Rasa"),
            Penggunaan(8,4,"ꦭ<font color='#3AA04B'>ꦫ</font>","Lara"),
            Penggunaan(9,5,"<font color='#3AA04B'>ꦏ</font>ꦩꦸ","Kamu"),
            Penggunaan(10,5,"ꦫ<font color='#3AA04B'>ꦏ</font>","Raka"),
            Penggunaan(11,6,"<font color='#3AA04B'>ꦢ</font>ꦒꦸ","Dagu"),
            Penggunaan(12,6,"<font color='#3AA04B'>ꦢ</font>ꦱꦶ","Dasi"),
            Penggunaan(13,7,"<font color='#3AA04B'>ꦠ</font>ꦲꦸ","Tahu"),
            Penggunaan(14,7,"ꦤ<font color='#3AA04B'>ꦠ</font>","Nata"),
            Penggunaan(15,8,"<font color='#3AA04B'>ꦱ</font>ꦥꦶ","Sapi"),
            Penggunaan(16,8,"ꦫ<font color='#3AA04B'>ꦱ</font>","Rasa"),
            Penggunaan(17,9,"<font color='#3AA04B'>ꦮ</font>ꦠꦶ","Wati"),
            Penggunaan(18,9,"<font color='#3AA04B'>ꦮ</font>ꦠꦸꦏ꧀","Watuk"),
            Penggunaan(19,10,"<font color='#3AA04B'>ꦭ</font>ꦤꦁ","Lanang"),
            Penggunaan(20,10,"ꦏꦼ<font color='#3AA04B'>ꦭ</font>ꦥ","Kelapa"),
            Penggunaan(21,11,"<font color='#3AA04B'>ꦥ</font>ꦏꦲꦶꦧꦗꦸ", "Pakai baju"),
            Penggunaan(22,11,"<font color='#3AA04B'>ꦥ</font>ꦱꦂꦒꦼꦝꦼ", "Pasar Gedhe"),
            Penggunaan(23,12,"<font color='#3AA04B'>ꦝ</font>ꦤꦶꦱꦸꦏꦏꦩꦸ", "Dhani suka kamu"),
            Penggunaan(24,12,"ꦤꦩꦏꦸꦫꦩ<font color='#3AA04B'>ꦝ</font>ꦤ", "Namaku Ramadhana"),
            Penggunaan(25,13,"<font color='#3AA04B'>ꦗ</font>ꦪꦧꦪ", "Jaya Baya"),
            Penggunaan(26,13,"<font color='#3AA04B'>ꦗ</font>ꦠꦶꦢꦶꦫꦶꦏꦸ", "Jati diriku"),
            Penggunaan(27,14,"<font color='#3AA04B'>ꦪ</font>ꦤꦩꦔꦤ꧀", "Yana Mangan"),
            Penggunaan(28,14,"<font color='#3AA04B'>ꦪ</font>ꦧꦲꦶꦢ꧀ꦱꦸꦤꦺ", "Yabai Děsuné"),
            Penggunaan(29,15,"ꦫꦲꦶꦩꦸ<font color='#3AA04B'>ꦚ</font>ꦥꦺꦴ", "Raimu Nyapo"),
            Penggunaan(30,15,"ꦲꦏꦸꦩꦼ<font color='#3AA04B'>ꦚ</font>ꦥꦏꦩꦸ", "Aku Menyapa Kamu"),
            Penggunaan(31,16,"<font color='#3AA04B'>ꦩ</font>ꦏꦧꦼꦒꦶꦠꦸ", "Maka Begitu"),
            Penggunaan(32,16,"<font color='#3AA04B'>ꦩ</font>ꦤꦸꦱꦶꦲꦲꦶꦤꦶ", "Manusia Ini"),
            Penggunaan(33,17,"<font color='#3AA04B'>ꦒ</font>ꦗꦃꦏꦼꦕꦶꦭ꧀", "Gajah Kecil"),
            Penggunaan(34,17,"<font color='#3AA04B'>ꦒ</font>ꦫꦶꦱ꧀ꦧꦶꦫꦸ", "Garis Biru"),
            Penggunaan(35,18,"<font color='#3AA04B'>ꦧ</font>ꦕꦧꦸꦏꦸ", "Baca Buku"),
            Penggunaan(36,18,"<font color='#3AA04B'>ꦧ</font>ꦫꦶꦱꦤ꧀ꦠꦶꦒ", "Barisan Tiga"),
            Penggunaan(37,19,"<font color='#3AA04B'>ꦠ</font>ꦭꦶꦧꦤ꧀ꦲꦫꦧ꧀", "Taliban Arab"),
            Penggunaan(38,19,"ꦩ<font color='#3AA04B'>ꦠ</font>ꦏꦸꦧꦼꦂꦱꦶꦤꦂ", "Mataku Bersinar"),
            Penggunaan(39,20,"<font color='#3AA04B'>ꦔ</font>ꦧꦫꦶꦲꦏꦸ", "Ngabari Aku"),
            Penggunaan(40,20,"<font color='#3AA04B'>ꦔ</font>ꦗꦼꦤꦶꦲꦮꦏ꧀", "Ngajeni Awak"),
            Penggunaan(41,21,"ꦩꦲꦶ<font color='#3AA04B'>ꦤ꧀ꦲꦼ</font>ꦧꦠ꧀", "main hebat"),
            Penggunaan(42,21,"ꦢꦭ<font color='#3AA04B'>ꦩ꧀ꦲ</font>ꦠꦶ", "dalam hati"),
            Penggunaan(43,22,"ꦭꦮ<font color='#3AA04B'>ꦤ꧀ꦤ</font>ꦒ", "lawan naga"),
            Penggunaan(44,22,"ꦲꦶꦏ<font color='#3AA04B'>ꦤ꧀ꦤꦶ</font>ꦭ", "ikan nila"),
            Penggunaan(45,23,"ꦧꦸꦏ<font color='#3AA04B'>ꦤ꧀ꦕꦼꦫꦶ</font>ꦠ", "bukan cerita"),
            Penggunaan(46,23,"ꦭꦮ<font color='#3AA04B'>ꦤ꧀ꦕꦫꦶ</font>ꦏ꧀", "lawan carik"),
            Penggunaan(47,24,"ꦩꦔ<font color='#3AA04B'>ꦤ꧀ꦫꦺꦴ</font>ꦠꦶ", "mangan roti"),
            Penggunaan(48,24,"ꦠꦸꦲ<font color='#3AA04B'>ꦤ꧀ꦫ</font>ꦥ꦳ꦶ", "tuan rafi"),
            Penggunaan(49,25,"ꦱꦶꦏ<font color='#3AA04B'>ꦠ꧀ꦏ</font>ꦩꦂ", "sikat kamar"),
            Penggunaan(50,25,"ꦧꦸꦏ<font color='#3AA04B'>ꦤ꧀ꦏ</font>ꦩꦸ", "bukan kamu"),
            Penggunaan(51,26,"ꦧꦼꦱꦺꦴ<font color='#3AA04B'>ꦏ꧀ꦢ</font>ꦠꦁ", "besok datang"),
            Penggunaan(52,26,"ꦩꦏ<font color='#3AA04B'>ꦤ꧀ꦢꦸ</font>ꦭꦸ", "makan dulu"),
            Penggunaan(53,27,"ꦭꦸ<font color='#3AA04B'>ꦥ꧀ꦠ</font>ꦭꦶꦧꦤ꧀", "lup taliban"),
            Penggunaan(54,27,"ꦩꦏ<font color='#3AA04B'>ꦤ꧀ꦠ</font>ꦲꦸ", "makan tahu"),
            Penggunaan(55,28,"ꦩꦏ<font color='#3AA04B'>ꦤ꧀ꦱ</font>ꦠꦼ", "makan sate"),
            Penggunaan(56,28,"ꦧꦸꦏ<font color='#3AA04B'>ꦤ꧀ꦱ</font>ꦪ", "bukan saya"),
            Penggunaan(57,29,"ꦧꦸꦏ<font color='#3AA04B'>ꦤ꧀ꦮ</font>ꦕꦤ", "bukan wacana"),
            Penggunaan(58,29,"ꦩꦏ<font color='#3AA04B'>ꦤ꧀ꦮ</font>ꦏ꧀ꦠꦸ", "makan waktu"),
            Penggunaan(59,30,"ꦩꦠꦶ<font color='#3AA04B'>ꦏꦤ꧀ꦭ</font>ꦩ꧀ꦥꦸ", "matikan lampu"),
            Penggunaan(60,30,"ꦲꦶ<font color='#3AA04B'>ꦏꦤ꧀ꦭ</font>ꦥꦸꦭꦥꦸ", "ikan lapulapu"),
            Penggunaan(61, 31, "ꦧꦸꦝ<font color='#3AA04B'>ꦭ꧀ꦥ</font>ꦫꦶꦱ꧀", "Budhal Paris"),
            Penggunaan(62, 31, "ꦩꦔ<font color='#3AA04B'>ꦤ꧀ꦥꦼ</font>ꦠꦼ", "Mangan Pete"),
            Penggunaan(63, 32, "ꦧꦥ<font color='#3AA04B'>ꦏ꧀ꦝ</font>ꦲꦂ", "Bapak Dhahar"),
            Penggunaan(64, 32, "ꦲꦼꦤ꧀ꦢꦺꦴ<font color='#3AA04B'>ꦒ꧀ꦝ</font>ꦢꦂ", "Endog Dhadar"),
            Penggunaan(65, 33, "ꦕꦼꦭꦸ<font color='#3AA04B'>ꦏ꧀ꦗ</font>ꦫꦺꦴꦠ꧀", "Celuk Jarot"),
            Penggunaan(66, 33, "ꦩꦔ<font color='#3AA04B'>ꦤ꧀ꦗ</font>ꦩ꧀ꦧꦸ", "Mangan Jambu"),
            Penggunaan(67, 34, "ꦤ꧀ꦢꦼꦭꦺꦴ<font color='#3AA04B'>ꦏ꧀ꦪ</font>ꦏꦸꦧ꧀", "Ndelok Yakub"),
            Penggunaan(68, 34, "ꦩ꧀ꦧꦺꦴꦠꦼ<font color='#3AA04B'>ꦤ꧀ꦪ</font>ꦏꦶꦤ꧀", "Mboten Yakin"),
            Penggunaan(69, 35, "ꦩ꧀ꦧꦺꦴꦠꦼ<font color='#3AA04B'>ꦤ꧀ꦚ</font>ꦩꦤ꧀", "Mboten Nyaman"),
            Penggunaan(70, 35, "ꦩ꧀ꦧꦺꦴꦠꦼ<font color='#3AA04B'>ꦤ꧀ꦚ</font>ꦩ꧀ꦧꦸꦁ", "Mboten Nyambung"),
            Penggunaan(71, 36, "ꦲꦉ<font color='#3AA04B'>ꦥ꧀ꦩ</font>ꦔꦤ꧀", "Arep Mangan"),
            Penggunaan(72, 36, "ꦲꦉ<font color='#3AA04B'>ꦥ꧀ꦩ</font>ꦱꦏ꧀", "Arep Masak"),
            Penggunaan(73, 37, "ꦩ꧀ꦧꦺꦴꦠꦼ<font color='#3AA04B'>ꦤ꧀ꦒ</font>ꦤ꧀ꦠꦼꦁ", "Mboten Ganteng"),
            Penggunaan(74, 37, "ꦩꦱ<font color='#3AA04B'>ꦏ꧀ꦒ</font>ꦫꦁꦲꦱꦼꦩ꧀", "Masak Garang Asem"),
            Penggunaan(75, 38, "ꦏꦸꦭꦸ<font color='#3AA04B'>ꦥ꧀ꦧ</font>ꦪꦼꦩ꧀", "Kulup Bayem"),
            Penggunaan(76, 38, "ꦤ꧀ꦢꦺꦴꦤ꧀ꦠꦺꦴ<font color='#3AA04B'>ꦏ꧀ꦧ</font>ꦒꦺꦴꦁ", "Ndontok Bagong"),
            Penggunaan(77, 39, "ꦩꦔ<font color='#3AA04B'>ꦤ꧀ꦠ</font>ꦲꦸ", "Mangan Tahu"),
            Penggunaan(78, 39, "ꦩꦔ<font color='#3AA04B'>ꦤ꧀ꦠ</font>ꦥꦼ", "Mangan Tape"),
            Penggunaan(79, 40, "ꦧꦸꦝ<font color='#3AA04B'>ꦭ꧀ꦔ</font>ꦤ꧀ꦗꦸꦏ꧀", "Budhal Nganjuk"),
            Penggunaan(80, 40, "ꦧꦸꦝ<font color='#3AA04B'>ꦭ꧀ꦔ</font>ꦮꦶ", "Budhal Ngawi"),
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

    fun getLencanas(): List<Lencana>{
        return listOf(
            Lencana(1, "Carakan Bronze", "https://static.vecteezy.com/system/resources/thumbnails/011/811/814/small/gold-wavy-circle-badge-free-png.png"),
            Lencana(2, "Carakan Silver", "https://static.vecteezy.com/system/resources/thumbnails/011/811/814/small/gold-wavy-circle-badge-free-png.png"),
            Lencana(3, "Carakan Gold", "https://static.vecteezy.com/system/resources/thumbnails/011/811/814/small/gold-wavy-circle-badge-free-png.png"),
            Lencana(4, "Carakan Gold", "https://static.vecteezy.com/system/resources/thumbnails/011/811/814/small/gold-wavy-circle-badge-free-png.png"),
        )
    }

}