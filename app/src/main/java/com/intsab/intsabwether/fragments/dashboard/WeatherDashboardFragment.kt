package com.intsab.intsabwether.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.intsab.core_domain.dataholders.CurrentDayWeatherB
import com.intsab.core_domain.params.CurrentDayWeatherParams
import com.intsab.intsabwether.R
import com.intsab.intsabwether.bottomsheets.ChangeCityBottomSheet
import com.intsab.intsabwether.databinding.FragmentWetherDetailsBinding
import com.intsab.intsabwether.di.DashboardViewModelFactory
import com.intsab.intsabwether.fragments.BaseFragment
import com.intsab.intsabwether.utils.GreetingsHelper
import com.intsab.intsabwether.utils.SharedPrefUtils
import javax.inject.Inject


class WeatherDashboardFragment : BaseFragment() {

    private var _binding: FragmentWetherDetailsBinding? = null
    private val binding get() = _binding!!
    private var cityName = ""

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
        setInitialData()
        setupViewListeners()
        setupObservers()
        getDashboardData()
    }

    private fun setInitialData() {
        cityName = SharedPrefUtils.getCityFromPreferences(requireContext())
        binding.location.text = cityName
        binding.greeting.text = GreetingsHelper().getGreeting(System.currentTimeMillis())

    }

    private fun setupViewListeners() {
        binding.nextDays.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_weekly_list)
        }
        binding.location.setOnClickListener {
            val bottomSheet = ChangeCityBottomSheet().apply {
                setOnCitySavedListener { city ->
                    saveCityToSharedPrefs(city)
                    getDashboardData()
                }
            }
            bottomSheet.show(childFragmentManager, ChangeCityBottomSheet.TAG)
        }
    }

    private fun saveCityToSharedPrefs(city: String) {
        SharedPrefUtils.saveCityToPreferences(requireContext(), city)
    }

    private fun getDashboardData() {
        toggleShimmer(true)
        cityName = SharedPrefUtils.getCityFromPreferences(requireContext())
        val params =
            CurrentDayWeatherParams(city = cityName)
        binding.location.text = cityName
        viewModel.getCurrentWeather(params)
    }

    private fun setupObservers() {
        viewModel.getCurrentWeatherLiveData.observe(viewLifecycleOwner, Observer { weather ->
            handleResponse(weather)
        })
        viewModel.error.observe(viewLifecycleOwner, Observer { reason ->
            toggleShimmer(false)
            binding.networkError.visibility = View.VISIBLE
        })

    }

    private fun handleResponse(weather: CurrentDayWeatherB) {
        toggleShimmer(false)
        binding.temperature.text = weather.tempC
        Glide.with(this).load(weather.weatherIcon).placeholder(R.drawable.placeholder)
            .into(binding.weatherIcon)

        binding.dateAndTime.text = weather.lastUpdated
        binding.weatherCondition.text = weather.shortDescription
        binding.feelsLike.text = "Feels Like " + weather.feelslikeC

        binding.windSpeed.text = "Wind Speed \n" + weather.windKph
        binding.heatIndex.text = "Heat Index \n" + weather.heatIndex

        binding.humidity.text = "Humidity\n" + weather.humidity
        binding.tvClouds.text = "Clouds\n" + weather.cloud
    }

    private fun toggleShimmer(show: Boolean) {
        binding.networkError.visibility = View.GONE
        if (show) {
            binding.mainLo.visibility = View.GONE
            binding.shimmerLayout.root.visibility = View.VISIBLE
        } else {
            binding.mainLo.visibility = View.VISIBLE
            binding.shimmerLayout.root.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        setInitialData()
    }
}