package com.project.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.project.habittracker.database.HabitDatabase
import com.project.habittracker.model.Habit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HabitViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val habitList = MutableLiveData<MutableList<Habit>>(mutableListOf())

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetchHabit(){

        launch {

            val db = HabitDatabase(getApplication())

            val list = db.habitDao().getAllHabit()

            habitList.postValue(list.toMutableList())

        }

    }

    fun insertHabit(habit: Habit){

        launch {

            val db = HabitDatabase(getApplication())

            db.habitDao().insertHabit(habit)

            fetchHabit()

        }

    }

    fun updateHabit(habit: Habit){

        launch {

            val db = HabitDatabase(getApplication())

            db.habitDao().updateHabit(habit)

            fetchHabit()

        }

    }

}