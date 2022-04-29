package com.example.animalsapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.animalsapp.R
import com.example.animalsapp.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

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
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        val id = arguments?.getString("ID")
        viewModel.getDetails(id).observe(viewLifecycleOwner){ details ->
            val detailsFact = root.findViewById<TextView>(R.id.fact)
            val createAt = root.findViewById<TextView>(R.id.time_create)
            val updateAt = root.findViewById<TextView>(R.id.time_update)
            val firstname = root.findViewById<TextView>(R.id.tv_firstname)
            val lastname = root.findViewById<TextView>(R.id.tv_lastname)
            val image = root.findViewById<ImageView>(R.id.tv_image)
            Picasso.get().load(details.user.photo).into(image)
            firstname.text = details.user.name.first
            lastname.text = details.user.name.last
            detailsFact.text = details.text
            createAt.text = details.createdAt.split("T")[0]
            updateAt.text = details.updatedAt.split("T")[0]
        }

        return root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
// //       viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
//
//    }

}