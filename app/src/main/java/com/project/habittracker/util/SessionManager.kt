package com.project.habittracker.util

import android.content.Context

class SessionManager(val context: Context) {

    private val prefName = "HabitSession"
    private val keyLogin = "isLogin"

    private val keyUsername = "username"

    private val sharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    fun saveLogin(username: String) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(keyLogin, true)
        editor.putString(keyUsername, username)
        editor.apply()
    }

    fun isLogin(): Boolean {
        return sharedPreferences.getBoolean(keyLogin, false)
    }

    fun getUsername(): String {
        return sharedPreferences.getString(keyUsername, "") ?: ""
    }
    fun logout() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}