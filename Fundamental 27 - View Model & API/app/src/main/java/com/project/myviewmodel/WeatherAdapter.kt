package com.project.myviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_items.view.*

// TODO 4: buat adapter
class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private val mData = ArrayList<WeatherItems>()

    fun setData(items: ArrayList<WeatherItems>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherAdapter.WeatherViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.weather_items, parent, false)
        return WeatherViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(weatherItems: WeatherItems){
            with(itemView){
                textCity.text = weatherItems.name
                textTemp.text = weatherItems.temperature
                textDesc.text = weatherItems.description
            }
        }
    }
}