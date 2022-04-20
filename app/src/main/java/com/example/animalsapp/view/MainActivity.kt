package com.example.animalsapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsapp.R

class MainActivity : AppCompatActivity() {
    val recyclerviewFragment: RecyclerviewFragment = RecyclerviewFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNewFragment(recyclerviewFragment)


//        val apiInterface = ApiInterface.create().getAnimals("cat,dog,horse,snail",100)
//
//        apiInterface.enqueue( object : Callback<List<Animal>>, CustomAdapter.ItemClickListener {
//            override fun onResponse(call: Call<List<Animal>>?, response: Response<List<Animal>>?) {
//                val adapter = CustomAdapter(response?.body() ?: emptyList(),this)
//                recyclerview.adapter = adapter
//            }
//
//            override fun onFailure(call: Call<List<Animal>>?, t: Throwable?) {
//
//            }
//
//            override fun onItemClick(_id: String) {
//                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
//                intent.putExtra("_id",_id)
//                startActivity(intent)
//            }
//        })
    }
    private fun setNewFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }
}