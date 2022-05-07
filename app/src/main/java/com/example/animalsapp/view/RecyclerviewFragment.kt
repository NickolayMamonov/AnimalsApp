package com.example.animalsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsapp.CustomAdapter
import com.example.animalsapp.MainScreenState
import com.example.animalsapp.R
import com.example.animalsapp.viewmodel.RecyclerviewViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RecyclerviewFragment : Fragment() {

    private lateinit var viewModel: RecyclerviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.recyclerview_fragment, container, false)
        viewModel = ViewModelProvider(this)[RecyclerviewViewModel::class.java]
        viewModel.dataState.onEach { datastate ->
            when(datastate){
                is MainScreenState.Content -> {
                    val progressbar = root.findViewById<ProgressBar>(R.id.progressBar)
                    progressbar.visibility= View.GONE
                    val errortext = root.findViewById<TextView>(R.id.error_text)
                    errortext.visibility= View.GONE
                    val adapter = CustomAdapter(datastate.animal) {
                        val ft = requireActivity().supportFragmentManager.beginTransaction()
                        ft.replace(R.id.fragment_container, DetailsFragment.newInstance(it))
                        ft.addToBackStack(null)
                        ft.commit()
                    }
                    val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                }
                is MainScreenState.Error -> {
                    val progressbar = root.findViewById<ProgressBar>(R.id.progressBar)
                    progressbar.visibility= View.INVISIBLE
                    val errortext = root.findViewById<TextView>(R.id.error_text)
                    errortext.visibility= View.VISIBLE
                }
                MainScreenState.Loading ->{
                    val progressbar = root.findViewById<ProgressBar>(R.id.progressBar)
                    progressbar.visibility= View.VISIBLE
                    val errortext = root.findViewById<TextView>(R.id.error_text)
                    errortext.visibility= View.INVISIBLE
                }
            }

        }.launchIn(lifecycleScope)
        return root
    }
}


