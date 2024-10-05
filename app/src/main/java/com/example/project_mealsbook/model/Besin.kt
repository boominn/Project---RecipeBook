package com.example.project_mealsbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity             //Room a hazır hale getiriyoruz gelen verileri internetten çekip local database e
                    // kaydedicez @ColumInfo ile sütunlarımızı oluşturduk ve her sütuna gelecek veriyi
                    // altına yazdık
data class Besin (

                                // Serializedname kullanma sebebimiz, eğer json içindeki
                                // verilerin anahtar isimleri çok saçma bir şey ise çift tırnak içine o
    @ColumnInfo(name ="isim")                            //anahtar ismini yazdıktan sonra alt satırına o verinin anahtar ismiyle aynı
    @SerializedName("isim")     // anahtar isminde değişken yapmamıza gerek kalmaz
    val besinIsim: String?,
    @ColumnInfo(name ="kalori")
    @SerializedName("kalori")
    val besinKalori: String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val besinKarbonhidrat: String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val besinProtein: String?,
    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    val besinYag: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val besinGorsel: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}