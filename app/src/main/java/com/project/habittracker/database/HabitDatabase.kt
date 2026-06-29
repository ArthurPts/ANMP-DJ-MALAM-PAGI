package com.project.habittracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.habittracker.dao.UserDao
import com.project.habittracker.model.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class HabitDatabase:RoomDatabase() {

    abstract fun userDao():UserDao

    companion object{

        @Volatile
        private var instance:HabitDatabase? = null

        private val LOCK = Any()

        private fun buildDatabase(context:Context)=
            Room.databaseBuilder(
                context.applicationContext,
                HabitDatabase::class.java,
                "habitdb"
            ).build()

        operator fun invoke(context:Context)=
            instance?: synchronized(LOCK){
                instance?:buildDatabase(context).also{
                    instance = it
                }
            }
    }

}