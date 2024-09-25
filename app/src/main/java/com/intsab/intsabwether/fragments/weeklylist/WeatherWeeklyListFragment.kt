package com.intsab.intsabwether.fragments.weeklylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.intsab.core_domain.params.WeeklyListWeatherParams
import com.intsab.intsabwether.databinding.FragmentWeeklyListBinding
import com.intsab.intsabwether.di.WeekListViewModelFactory
import com.intsab.intsabwether.fragments.BaseFragment
import com.intsab.intsabwether.fragments.weeklylist.adapter.WeatherAdapter
import com.intsab.intsabwether.utils.SharedPrefUtils
import javax.inject.Inject

class WeatherWeeklyListFragment : BaseFragment() {
    private var _binding: FragmentWeeklyListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: WeekListViewModelFactory

    private lateinit var viewModel: WeeklyListViewModel
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeeklyListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WeeklyListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInitialData()
        setupObservers()
        getWeeklyListApiCall()
    }

    private fun setupInitialData() {
        weatherAdapter = WeatherAdapter(requireContext(), emptyList())
        binding.city.text = SharedPrefUtils.getCityFromPreferences(requireContext())
        binding.rvWeekDays.adapter = weatherAdapter
        binding.rvWeekDays.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun getWeeklyListApiCall() {
        toggleShimmer(true)
        val params =
            WeeklyListWeatherParams("7", SharedPrefUtils.getCityFromPreferences(requireContext()))
        viewModel.getFullWeekList(params)
    }

    private fun setupObservers() {
        viewModel.getFullWeekWeatherLiveData.observe(viewLifecycleOwner, Observer { weatherList ->
            toggleShimmer(false)
            weatherAdapter = WeatherAdapter(requireContext(), weatherList)
            binding.rvWeekDays.adapter = weatherAdapter
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { reason ->
            toggleShimmer(false)
            Toast.makeText(requireContext(), reason, Toast.LENGTH_SHORT).show()
        })
    }

    private fun toggleShimmer(show: Boolean) {
        if (show) {
            binding.rvWeekDays.visibility = View.GONE
            binding.shimmerLayout.root.visibility = View.VISIBLE
        } else {
            binding.rvWeekDays.visibility = View.VISIBLE
            binding.shimmerLayout.root.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clean up the binding
    }
}
