package com.intsab.intsabwether.fragments.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.intsab.core_domain.params.CurrentDayWeatherParams
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

        viewModel.getCurrentWeatherLiveData.observe(
            viewLifecycleOwner,
            Observer { weather ->
                Log.d("", "onViewCreated: ")
            })

        // Call the API to get current weather
        val params = CurrentDayWeatherParams(city = "New York")  // Example parameter
        viewModel.getCurrentWeather(params)

    }
}