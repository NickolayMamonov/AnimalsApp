package com.example.animalsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsapp.CustomAdapter
import com.example.animalsapp.R

class RecyclerviewFragment : Fragment() {

    companion object {
        fun newInstance() = RecyclerviewFragment()
    }

    private lateinit var viewModel: RecyclerviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.recyclerview_fragment, container, false)
        viewModel = ViewModelProvider(this).get(RecyclerviewViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner) { animals ->
            val adapter = CustomAdapter(animals) {
                val ft = requireActivity().supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_container, DetailsFragment())
                ft.addToBackStack(null)
                ft.commit()
            }
            val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            val fragment = Fragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            fragment.arguments = bundle
        }

        return root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(RecyclerviewViewModel::class.java)
//
//    }

}


