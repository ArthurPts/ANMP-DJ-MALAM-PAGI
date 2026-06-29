package com.project.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.project.habittracker.database.HabitDatabase
import com.project.habittracker.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val loginResult = MutableLiveData<Boolean>()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun login(username:String,password:String){

        launch{

            val db = HabitDatabase(getApplication())

            if(db.userDao().getUserCount()==0){
                db.userDao().insertAll(
                    User("student","123")
                )
            }

            val user = db.userDao().login(username,password)

            loginResult.postValue(user!=null)

        }

    }

}