package com.project.habittracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.habittracker.model.Habit

class HabitViewModel : ViewModel() {

    val habitList = MutableLiveData<MutableList<Habit>>(mutableListOf())
}