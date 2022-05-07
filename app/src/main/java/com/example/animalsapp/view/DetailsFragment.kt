package com.example.animalsapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.animalsapp.DetailsScreenState
import com.example.animalsapp.R
import com.example.animalsapp.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        viewModel.dataState.onEach {datastate ->
            when(datastate){
                is DetailsScreenState.Content -> {
                    val progressbar = root.findViewById<ProgressBar>(R.id.progressBar_details)
                    progressbar.visibility= View.GONE
                    val errtext = root.findViewById<TextView>(R.id.error_text)
                    errtext.visibility= View.GONE
                    val detailsFact = root.findViewById<TextView>(R.id.fact)
                    val createAt = root.findViewById<TextView>(R.id.time_create)
                    val updateAt = root.findViewById<TextView>(R.id.time_update)
                    val firstname = root.findViewById<TextView>(R.id.tv_firstname)
                    val lastname = root.findViewById<TextView>(R.id.tv_lastname)
                    val image = root.findViewById<ImageView>(R.id.tv_image)
                    Picasso.get().load(datastate.id.user.photo).into(image)
                    firstname.text = datastate.id.user.name.first
                    lastname.text = datastate.id.user.name.last
                    detailsFact.text = datastate.id.text
                    createAt.text = datastate.id.createdAt.split("T")[0]
                    updateAt.text = datastate.id.updatedAt.split("T")[0]
                }
                is DetailsScreenState.Error -> {
                    val progressbar = root.findViewById<ProgressBar>(R.id.progressBar_details)
                    progressbar.visibility= View.INVISIBLE
                    val errtext = root.findViewById<TextView>(R.id.error_text)
                    errtext.visibility= View.VISIBLE
                }
                DetailsScreenState.Loading -> {
                    val progressbar = root.findViewById<ProgressBar>(R.id.progressBar_details)
                    progressbar.visibility= View.VISIBLE
                    val errtext = root.findViewById<TextView>(R.id.error_text)
                    errtext.visibility= View.INVISIBLE
                }
            }
        }.launchIn(lifecycleScope)
        viewModel.getDetails(id)
        return root
    }

}