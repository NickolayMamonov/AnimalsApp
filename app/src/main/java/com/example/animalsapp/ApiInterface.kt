package com.example.animalsapp

import com.example.animalsapp.data.Animal
import com.example.animalsapp.data.AnimalDetails
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("facts/random")
    fun getAnimals(@Query ("animal_type") animal_type : String,
                   @Query ("amount") amount : Int) : Call<List<Animal>>
    @GET("facts/{_id}")
    fun getFactsDetails(@Path("_id") _id : String): Call<AnimalDetails>

    companion object {

        var BASE_URL = "https://cat-fact.herokuapp.com/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}