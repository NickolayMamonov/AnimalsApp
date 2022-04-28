package com.example.animalsapp.view

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalsapp.ApiInterface
import com.example.animalsapp.models.animaldetails.AnimalDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsViewModel : ViewModel() {
    var details: MutableLiveData<String>? = null

    fun getDetails(): LiveData<List<AnimalDetails>> {
        val mutableLiveData = MutableLiveData<List<AnimalDetails>>()
//        val apiInterface = ApiInterface.create().getFactsDetails()
        val apiInterface = bundle?.let { ApiInterface.create().getFactsDetails(it) }
        apiInterface.enqueue( object : Callback<List<AnimalDetails>> {
            override fun onResponse(call: Call<List<AnimalDetails>>?, response: Response<List<AnimalDetails>>?) {
                if (response != null) {
                    mutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<AnimalDetails>>?, t: Throwable?) {

            }

        })
        return mutableLiveData
    }
}