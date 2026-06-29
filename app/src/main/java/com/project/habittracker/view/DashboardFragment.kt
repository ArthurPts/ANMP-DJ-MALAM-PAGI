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
import com.project.habittracker.model.Habit
import com.project.habittracker.view.HabitAdapter
import com.project.habittracker.util.FileHelper
import com.project.habittracker.viewmodel.HabitViewModel
import com.project.habittracker.R

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var adapter: HabitAdapter
    private lateinit var viewModel: HabitViewModel
    private lateinit var fileHelper: FileHelper

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
        fileHelper = FileHelper(requireContext())
//        fileHelper.clearData()

        val data = fileHelper.readFromFile().map {
            if (it.iconName.isNullOrEmpty()) {
                it.copy(iconName = "ic_default")
            } else it
        }.toMutableList()


        viewModel.habitList.value = data

        adapter = HabitAdapter(viewModel.habitList.value!!) {
            fileHelper.writeToFile(viewModel.habitList.value!!)
        }

        binding.recyclerViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewHabit.adapter = adapter

        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Habit>("newHabit")
            ?.observe(viewLifecycleOwner) { habit ->
                habit?: return@observe

                viewModel.habitList.value!!.add(habit)
                adapter.notifyItemInserted(viewModel.habitList.value!!.size - 1)
                fileHelper.writeToFile(viewModel.habitList.value!!)

                findNavController().currentBackStackEntry
                    ?.savedStateHandle
                    ?.remove<Habit>("newHabit")
            }

        binding.fabAdd.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardToCreate()
            findNavController().navigate(action)
        }
    }
}