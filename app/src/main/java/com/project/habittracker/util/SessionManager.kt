package com.project.habittracker.util

import android.content.Context

class SessionManager(val context: Context) {

    private val prefName = "HabitSession"
    private val keyLogin = "isLogin"

    private val sharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    fun saveLogin() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(keyLogin, true)
        editor.apply()
    }

    fun isLogin(): Boolean {
        return sharedPreferences.getBoolean(keyLogin, false)
    }

}