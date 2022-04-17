package com.example.animalsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsapp.data.Animal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val apiInterface = ApiInterface.create().getAnimals("cat,dog,horse,snail",100)

        apiInterface.enqueue( object : Callback<List<Animal>>,CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<List<Animal>>?, response: Response<List<Animal>>?) {
                val adapter = CustomAdapter(response?.body(),this)
                recyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<List<Animal>>?, t: Throwable?) {

            }

            override fun onItemClick(_id: String) {
                val intent = Intent(this@MainActivity,DetailsActivity::class.java)
                intent.putExtra("_id",_id)
                startActivity(intent)
            }
        })
    }
}