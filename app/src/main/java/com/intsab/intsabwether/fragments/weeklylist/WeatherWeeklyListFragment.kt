package com.intsab.intsabwether.fragments.weeklylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intsab.intsabwether.databinding.FragmentWeeklyListBinding
import com.intsab.intsabwether.fragments.BaseFragment

class WeatherWeeklyListFragment : BaseFragment() {
    private var _binding: FragmentWeeklyListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeeklyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}