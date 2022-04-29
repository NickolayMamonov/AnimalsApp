package com.example.animalsapp.viewmodel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalsapp.ApiInterface
import com.example.animalsapp.CustomAdapter
import com.example.animalsapp.models.animal.Animal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerviewViewModel : ViewModel() {
//    var data: MutableLiveData<String>? = null

    fun getData(): LiveData<List<Animal>> {
        val mutableLiveData = MutableLiveData<List<Animal>>()
        val apiInterface = ApiInterface.create().getAnimals("cat,dog,horse,snail",25)
        apiInterface.enqueue( object : Callback<List<Animal>> {
            override fun onResponse(call: Call<List<Animal>>, response: Response<List<Animal>>) {
                mutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Animal>>, t: Throwable) {

            }

        })
        return mutableLiveData
    }

}