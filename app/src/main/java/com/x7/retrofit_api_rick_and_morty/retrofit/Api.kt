package com.x7.retrofit_api_rick_and_morty.retrofit

import com.x7.retrofit_api_rick_and_morty.model.RickandMortyModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("character")
    fun getAllProducts():Call<RickandMortyModel>
    //https://rickandmortyapi.com/api/character

    @GET("character")
    fun getAlldata(
        @Query("page") page:Int
    ):Call<RickandMortyModel>

}