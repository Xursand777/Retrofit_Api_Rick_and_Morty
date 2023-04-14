package com.x7.retrofit_api_rick_and_morty.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitIntance {

    //https://rickandmortyapi.com/api/character
    val retrofit=Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(Api::class.java)
}