package com.intsab.intsabwether.fragments.weeklylist.adapter

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intsab.core_domain.dataholders.FullWeekWeatherB
import com.intsab.intsabwether.databinding.WeekDayItemLayoutBinding

class WeatherAdapter(private val dailyWeatherList: List<FullWeekWeatherB>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding =
            WeekDayItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val dailyWeather = dailyWeatherList[position]
        holder.bind(dailyWeather)
    }

    override fun getItemCount(): Int {
        return dailyWeatherList.size
    }
    class WeatherViewHolder(private val binding: WeekDayItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dailyWeather: FullWeekWeatherB) {
            binding.dayOfWeek.text = dailyWeather.date
//            binding.dailyWeatherIcon.setImageResource(dailyWeather.icon)
            binding.weatherDescription.text = dailyWeather.temperature
            binding.dailyTemperature.text = dailyWeather.temperature
        }
    }
}
