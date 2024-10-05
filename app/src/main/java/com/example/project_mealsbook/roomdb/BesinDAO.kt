package com.example.project_mealsbook.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.project_mealsbook.model.Besin

@Dao            //Data acces object    yani database işlemlerimizin fonksiyonlarını yazıcaz add
                // delete gibi gibi
interface BesinDAO {

                             // biz bu projede local veritabanını gelen tüm verileri kaydetmek için
                             // değil de gelen verileri cash olarak yani geçici olarak kaydedip daha
    @Query("DELETE FROM besin")                  // sonra ekranda gösterip ardından veritabanından sileceğiz
    suspend fun  deleteAll()

    @Query("SELECT * FROM besin")
    suspend fun getAllBesin(): List<Besin>

    @Query("SELECT * FROM besin WHERE uuid = :besinId")  // burda besin id sini fonksiyon içinde
    suspend fun getBesin(besinId: Int) : Besin    //parametre olarak alıcaz ve foksiyon bize tek bir
                                                  // besin döndürecek yani id sini girdiğimiz besini

    @Insert
    suspend fun insertAll(vararg besin: Besin) : List<Long> //eklediği besinlerin id sini long olarak
                                                            // geri dönüyor

}