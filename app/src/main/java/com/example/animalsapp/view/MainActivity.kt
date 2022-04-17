package com.example.animalsapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsapp.ApiInterface
import com.example.animalsapp.CustomAdapter
import com.example.animalsapp.R
import com.example.animalsapp.models.animal.Animal
import com.example.animalsapp.viewmodel.AnimalsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var aViewModel:AnimalsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        aViewModel = AnimalsViewModel()
        val result = aViewModel.getAnimals()

        val apiInterface = ApiInterface.create().getAnimals("cat,dog,horse,snail",100)

        apiInterface.enqueue( object : Callback<List<Animal>>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<List<Animal>>?, response: Response<List<Animal>>?) {
                val adapter = CustomAdapter(response?.body() ?: emptyList(),this)
                recyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<List<Animal>>?, t: Throwable?) {

            }

            override fun onItemClick(_id: String) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("_id",_id)
                startActivity(intent)
            }
        })
    }
}