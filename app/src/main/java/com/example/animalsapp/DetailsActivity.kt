package com.example.animalsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.animalsapp.data.AnimalDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    private lateinit var facts: TextView
    private lateinit var createAt: TextView
    private lateinit var updateAt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val id = intent.getStringExtra("_id")
        facts = findViewById(R.id.fact)
        createAt = findViewById(R.id.time_create)
        updateAt = findViewById(R.id.time_update)
//        val apiInterface = id?.let { ApiInterface.create().getFactsDetails(it) }
//        apiInterface?.enqueue( object : Callback<AnimalDetails> {
//            override fun onResponse(call: Call<AnimalDetails>?, response: Response<AnimalDetails>?) {
//                facts.text= response?.body()?.text
//                createAt.text = response?.body()?.createdAt
//                updateAt.text = response?.body()?.updatedAt
//            }
//            override fun onFailure(call: Call<AnimalDetails>?, t: Throwable?) {
//                Log.d("testLogs","onFailure : ${t?.message}")
//            }
//        })
    }
}