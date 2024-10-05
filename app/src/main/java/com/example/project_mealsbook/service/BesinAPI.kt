package com.example.project_mealsbook.service

import com.example.project_mealsbook.model.Besin
import retrofit2.http.GET

interface BesinAPI {

    // BASE URL -> https://raw.githubusercontent.com/
    // ENDPOINT -> atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    suspend fun getBesin(): List<Besin>


}