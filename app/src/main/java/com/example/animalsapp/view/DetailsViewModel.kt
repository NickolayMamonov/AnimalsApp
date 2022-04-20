package com.example.animalsapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalsapp.ApiInterface
import com.example.animalsapp.models.animal.Animal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {
    var details: MutableLiveData<String>? = null

    fun getDetails(): LiveData<List<Animal>> {
        val mutableLiveData = MutableLiveData<List<Animal>>()
        val apiInterface = ApiInterface.create().getAnimals("cat,dog,horse,snail",100)
        apiInterface.enqueue( object : Callback<List<Animal>> {
            override fun onResponse(call: Call<List<Animal>>?, response: Response<List<Animal>>?) {
                if (response != null) {
                    mutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Animal>>?, t: Throwable?) {

            }

        })
        return mutableLiveData
    }
}