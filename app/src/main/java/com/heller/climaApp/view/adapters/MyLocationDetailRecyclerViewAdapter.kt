package com.heller.climaApp.view.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.heller.climaApp.models.ConsolidatedWeather
import com.heller.climaApp.R
import com.heller.climaApp.apiServices.URL_IMG
import com.heller.climaApp.roundDecimal
import java.lang.StringBuilder

class MyLocationDetailRecyclerViewAdapter() : RecyclerView.Adapter<MyLocationDetailRecyclerViewAdapter.ViewHolder>() {

    private var values:List<ConsolidatedWeather> = emptyList()
    private lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_detail, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textTemp.text = StringBuilder()
            .append(roundDecimal(values[position].the_temp))
            .append("°")

        holder.textViewMsg.text = values[position].weather_state_name
        holder.textDate.text = values[position].applicable_date

        holder.textMinMax.text = StringBuilder()
            .append("Maximum: ")
            .append(roundDecimal(values[position].max_temp)).append("°")
            .append(" ").append("Minimum: ")
            .append(roundDecimal(values[position].min_temp)).append("°")

        Glide.with(context).load(
                StringBuilder()
                    .append(URL_IMG)
                    .append(values[position].weather_state_abbr)
                    .append(".png").toString()
        ).into(holder.imageW)

    }

    override fun getItemCount(): Int = values.size

    fun updateItems(values: List<ConsolidatedWeather>) {
        this.values = values
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTemp: TextView = view.findViewById(R.id.textTemp)
        val textViewMsg:TextView = view.findViewById(R.id.textViewMsg)
        val imageW:ImageView = view.findViewById(R.id.imageW)
        val textDate:TextView = view.findViewById(R.id.textDate)
        val textMinMax:TextView = view.findViewById(R.id.textMinMax)
    }
}

