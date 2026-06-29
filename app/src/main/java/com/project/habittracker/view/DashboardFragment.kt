package com.project.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.habittracker.databinding.FragmentDashboardBinding
import com.project.habittracker.viewmodel.HabitViewModel
import com.project.habittracker.R

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var adapter: HabitAdapter
    private lateinit var viewModel: HabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]

        viewModel.fetchHabit()

        viewModel.habitList.observe(viewLifecycleOwner) {

            adapter = HabitAdapter(it) { habit ->

                viewModel.updateHabit(habit)

            }

            binding.recyclerViewHabit.layoutManager =
                LinearLayoutManager(context)

            binding.recyclerViewHabit.adapter = adapter

        }

        binding.fabAdd.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardToCreate()
            findNavController().navigate(action)
        }

    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchHabit()
    }
}