package com.project.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.habittracker.databinding.FragmentEditHabitBinding
import com.project.habittracker.model.Habit
import androidx.navigation.fragment.findNavController
import android.widget.ArrayAdapter
import com.project.habittracker.viewmodel.HabitViewModel
import androidx.lifecycle.ViewModelProvider

class EditHabitFragment : Fragment(), EditHabitListener {

    private lateinit var binding: FragmentEditHabitBinding
    private lateinit var viewModel: HabitViewModel

    private lateinit var habit: Habit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditHabitBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]

        habit = arguments?.getSerializable("habit") as Habit

        binding.habit = habit
        binding.lifecycleOwner = viewLifecycleOwner

        binding.listener = this

        val iconList = listOf("water", "books", "muscle", "meditation")

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            iconList
        )

        binding.spinnerIcon.setAdapter(adapter)

//        binding.edtName.setText(habit.name)
//        binding.edtDesc.setText(habit.description)
//        binding.edtTarget.setText(habit.target.toString())
//        binding.edtUnit.setText(habit.unit)
//        binding.edtStep.setText(habit.step.toString())
        binding.spinnerIcon.setText(habit.iconName, false)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

//        binding.btnSubmit.setOnClickListener {
//
//            val updatedHabit = Habit(
//                id = habit.id,
//                name = binding.edtName.text.toString(),
//                description = binding.edtDesc.text.toString(),
//                progress = habit.progress,
//                target = binding.edtTarget.text.toString().toInt(),
//                unit = binding.edtUnit.text.toString(),
//                step = binding.edtStep.text.toString().toInt(),
//                iconName = binding.spinnerIcon.text.toString()
//            )
//
//            viewModel.updateHabit(updatedHabit)
//
//            findNavController().navigateUp()
//        }
//        binding.btnSubmit.setOnClickListener {
//
//            habit.iconName = binding.spinnerIcon.text.toString()
//
//            viewModel.updateHabit(habit)
//
//            findNavController().navigateUp()
//        }
    }

    override fun onClick(v: View) {

        habit.target = binding.edtTarget.text.toString().toIntOrNull() ?: 0
        habit.step = binding.edtStep.text.toString().toIntOrNull() ?: 0
        habit.iconName = binding.spinnerIcon.text.toString()

        viewModel.updateHabit(habit)

        findNavController().navigateUp()
    }
}