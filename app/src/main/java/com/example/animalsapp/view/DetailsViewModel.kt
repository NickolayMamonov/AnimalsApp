package com.example.animalsapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalsapp.ApiInterface
import com.example.animalsapp.models.animaldetails.AnimalDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsViewModel : ViewModel() {
    //var details: MutableLiveData<String>? = null

    fun getDetails(id: String?): LiveData<AnimalDetails> {
        val mutableLiveData = MutableLiveData<AnimalDetails>()
        val apiInterface = ApiInterface.create().getFactsDetails(id)
//        val apiInterface = id?.let { ApiInterface.create().getFactsDetails(it) }
        apiInterface.enqueue( object : Callback<AnimalDetails> {
            override fun onResponse(call: Call<AnimalDetails>?, response: Response<AnimalDetails>?) {
                if (response != null) {
                    mutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<AnimalDetails>?, t: Throwable?) {

            }



        })
        return mutableLiveData
    }
}