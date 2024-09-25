package com.intsab.intsabwether.fragments.weeklylist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.intsab.core_domain.params.WeeklyListWeatherParams
import com.intsab.intsabwether.databinding.FragmentWeeklyListBinding
import com.intsab.intsabwether.di.WeekListViewModelFactory
import com.intsab.intsabwether.fragments.BaseFragment
import javax.inject.Inject

class WeatherWeeklyListFragment : BaseFragment() {
    private var _binding: FragmentWeeklyListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: WeekListViewModelFactory

    private lateinit var viewModel: WeeklyListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeeklyListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WeeklyListViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        getWeeklyListApiCall()
    }

    private fun getWeeklyListApiCall() {
        val params = WeeklyListWeatherParams("7", "Dubai")
        viewModel.getFullWeekList(params)
    }

    private fun setupObservers() {
        viewModel.getFullWeekWeatherLiveData.observe(
            viewLifecycleOwner,
            Observer { weatherList ->
                Log.d("", "onViewCreated: ")
            })
    }
}