package com.project.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.habittracker.databinding.FragmentCreateHabitBinding
import com.project.habittracker.model.Habit

class CreateHabitFragment : Fragment() {

    private lateinit var binding: FragmentCreateHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val iconList = listOf("water", "books", "muscle", "meditation")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, iconList)
        binding.spinnerIcon.setAdapter(adapter)

        binding.btnCreate.setOnClickListener {

            val name = binding.edtName.text.toString()
            val desc = binding.edtDesc.text.toString()
            val targetStr = binding.edtTarget.text.toString()
            val unit = binding.edtUnit.text.toString()
            val stepStr = binding.edtStep.text.toString()
            val iconName = binding.spinnerIcon.text.toString()

            if (
                name.isEmpty() ||
                desc.isEmpty() ||
                targetStr.isEmpty() ||
                unit.isEmpty() ||
                stepStr.isEmpty() ||
                iconName.isEmpty()
            ) {
                Toast.makeText(requireContext(), "Semua data harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val target = targetStr.toInt()
            val step = stepStr.toInt()

            if (step > target) {
                Toast.makeText(requireContext(), "Step tidak boleh lebih besar dari target", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (step <= 0) {
                Toast.makeText(requireContext(), "Step harus lebih dari 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newHabit = Habit(
                name = name,
                description = desc,
                progress = 0,
                target = target,
                unit = unit,
                step = step,
                iconName = iconName
            )

            findNavController().previousBackStackEntry
                ?.savedStateHandle
                ?.set("newHabit", newHabit)

            findNavController().navigateUp()
        }
    }
}