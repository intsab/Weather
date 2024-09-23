package com.intsab.intsabwether.fragments.weeklylist

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intsab.intsabwether.R

class WeeklyListFragment : Fragment() {

    companion object {
        fun newInstance() = WeeklyListFragment()
    }

    private val viewModel: WeeklyListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_weekly_list, container, false)
    }
}