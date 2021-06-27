package com.heller.climaApp.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.heller.climaApp.view.LocationListener
import com.heller.climaApp.R
import com.heller.climaApp.apiServices.LocationBean

class MyLocationRecyclerViewAdapter(private var mlistener: LocationListener) : RecyclerView.Adapter<MyLocationRecyclerViewAdapter.ViewHolder>() {

    private var values:List<LocationBean> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.textViewType.text = values[position].locationType
        holder.textViewCity.text = values[position].title
        holder.card.setOnClickListener { mlistener.onClickLocation(item) }
    }

    override fun getItemCount(): Int = values.size

    fun updateItems(values: List<LocationBean>) {
        this.values = values
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewType: TextView = view.findViewById(R.id.textViewType)
        val textViewCity:TextView = view.findViewById(R.id.textViewCity)
        val card:CardView = view.findViewById(R.id.card)
    }
}

