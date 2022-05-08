package com.example.animalsapp

import com.example.animalsapp.models.animal.Animal
import com.example.animalsapp.models.animaldetails.AnimalDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("facts/random")
    suspend fun getAnimals(@Query ("animal_type") animal_type : String,
                           @Query ("amount") amount : Int) : List<Animal>
    @GET("facts/{id}")
    suspend fun getFactsDetails(@Path("id") id: String?): AnimalDetails

    companion object {

        private var BASE_URL = "https://cat-fact.herokuapp.com/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}