package com.example.animalsapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.animalsapp.R

class MainActivity : AppCompatActivity() {
    private val recyclerviewFragment: RecyclerviewFragment = RecyclerviewFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNewFragment(recyclerviewFragment)

    }
    private fun setNewFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }
}