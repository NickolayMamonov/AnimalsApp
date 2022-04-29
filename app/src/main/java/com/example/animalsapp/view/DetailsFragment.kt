package com.example.animalsapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalsapp.R

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance(id:String): DetailsFragment{
            val fragment = DetailsFragment()
            val bnd = Bundle()
            bnd.putString("ID",id)
            fragment.arguments=bnd
            return fragment
        }
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.details_fragment, container, false)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val id = arguments?.getString("ID")
        viewModel.getDetails(id).observe(viewLifecycleOwner){ details ->
//            val constraintLayout = root.findViewById<ConstraintLayout>(R.id.details_body_container_constraintlayout)
            val detailsFact = root.findViewById<TextView>(R.id.fact)
            val createAt = root.findViewById<TextView>(R.id.time_create)
            val updateAt = root.findViewById<TextView>(R.id.time_update)
            detailsFact.text = details.text
            createAt.text = details.createdAt.split("T")[0]
            updateAt.text = details.updatedAt.split("T")[0]

        }
       // val constraintLayout = root.findViewById<ConstraintLayout>(R.id.details_body_container_constraintlayout)

        return root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
// //       viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
//
//    }

}