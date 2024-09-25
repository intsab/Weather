package com.intsab.intsabwether.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.intsab.core_domain.dataholders.CurrentDayWeatherB
import com.intsab.core_domain.params.CurrentDayWeatherParams
import com.intsab.intsabwether.R
import com.intsab.intsabwether.databinding.FragmentWetherDetailsBinding
import com.intsab.intsabwether.di.DashboardViewModelFactory
import com.intsab.intsabwether.fragments.BaseFragment
import javax.inject.Inject


class WeatherDashboardFragment : BaseFragment() {

    private var _binding: FragmentWetherDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: DashboardViewModelFactory

    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWetherDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewListeners()
        setupObservers()
        getDashboardData()
    }

    private fun setupViewListeners() {
        binding.nextDays.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_weekly_list)
        }
    }

    private fun getDashboardData() {
        toggleShimmer(true)
        val params = CurrentDayWeatherParams(city = "New York")
        viewModel.getCurrentWeather(params)
    }

    private fun setupObservers() {
        viewModel.getCurrentWeatherLiveData.observe(
            viewLifecycleOwner,
            Observer { weather ->
                handleResponse(weather)
            })
        viewModel.error.observe(
            viewLifecycleOwner,
            Observer { reason ->
                Toast.makeText(requireContext(), reason, Toast.LENGTH_SHORT).show()

            })

    }

    private fun handleResponse(weather: CurrentDayWeatherB) {
        toggleShimmer(false)
        Glide
            .with(this)
            .load(weather.weatherIcon)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.weatherIcon)

        binding.dateTime.text = weather.lastUpdated
        binding.temperature.text = weather.tempC
        binding.humidity.text = weather.humidity
    }

    private fun toggleShimmer(show: Boolean) {
        if (show) {
            binding.mainLo.visibility = View.GONE
            binding.shimmerLayout.root.visibility = View.VISIBLE
        } else {
            binding.mainLo.visibility = View.VISIBLE
            binding.shimmerLayout.root.visibility = View.GONE
        }
    }
}