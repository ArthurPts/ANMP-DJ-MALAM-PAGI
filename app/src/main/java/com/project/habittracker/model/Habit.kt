package com.project.habittracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Habit(

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,

    val name:String,
    val description:String,
    var progress:Int,
    val target:Int,
    val unit:String,
    val step:Int,
    val iconName:String? = null

):Serializable