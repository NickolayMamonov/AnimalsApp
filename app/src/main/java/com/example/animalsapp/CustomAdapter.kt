package com.example.animalsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsapp.data.Animal

class CustomAdapter(private val mList: List<Animal>?,
                    val mItemClickListener: ItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(_id: String)
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val Animal = mList?.get(position)

        // sets the image to the imageview from our itemHolder class
        // sets the text to the textview from our itemHolder class
        holder.textView.text = Animal?.type

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList!!.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        init {
            ItemView.setOnClickListener {
                mList?.get(position)?._id?.let { it -> mItemClickListener.onItemClick(it) }
            }
        }
    }
}
