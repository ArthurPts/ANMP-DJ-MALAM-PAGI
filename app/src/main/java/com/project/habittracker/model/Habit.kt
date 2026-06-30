package com.project.habittracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Habit(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var username: String,
    var name: String,
    var description: String,
    var progress: Int,
    var target: Int,
    var unit: String,
    var step: Int,
    var iconName: String? = null

) : Serializable {

    val status: String
        get() = if (progress >= target) "Completed" else "In Progress"
}