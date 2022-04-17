package com.example.animalsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsapp.models.animal.Animal

class CustomAdapter(private val mList: List<Animal>,
                    private val mItemClickListener: ItemClickListener
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(_id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val animal = mList[position]
        holder.textView.text = animal.type
        holder.itemView.setOnClickListener {
            mList[position]._id.let { _id -> mItemClickListener.onItemClick(_id) }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
//        init {
//            ItemView.setOnClickListener {
//                mList?.get(position)?._id?.let { it -> mItemClickListener.onItemClick(it) }
//            }
//        }
    }
}
