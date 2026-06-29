package com.project.habittracker.model

import java.io.Serializable

data class Habit(
    val name: String,
    val description: String,
    var progress: Int,
    val target: Int,
    val unit: String,
    val step: Int,
    val iconName: String?=null,
) : Serializable