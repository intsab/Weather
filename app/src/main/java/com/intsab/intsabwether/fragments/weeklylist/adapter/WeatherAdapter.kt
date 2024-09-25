package com.intsab.intsabwether.fragments.weeklylist.adapter

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intsab.core_domain.dataholders.FullWeekWeatherB
import com.intsab.intsabwether.R
import com.intsab.intsabwether.databinding.WeekDayItemLayoutBinding

class WeatherAdapter(
    private val context: Context,
    private val dailyWeatherList: List<FullWeekWeatherB>
) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding =
            WeekDayItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val dailyWeather = dailyWeatherList[position]
        holder.bind(dailyWeather)
    }

    override fun getItemCount(): Int {
        return dailyWeatherList.size
    }

    class WeatherViewHolder(
        private val context: Context,
        private val binding: WeekDayItemLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: FullWeekWeatherB) {
            Glide.with(context).load(weather.icon).placeholder(R.drawable.placeholder)
                .into(binding.dailyWeatherIcon)

            binding.weatherDescription.text = weather.condition
            binding.dateDay.text = weather.date
            binding.tvTemprature.text = weather.temperature
        }
    }
}
