package com.project.habittracker.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.habittracker.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment
import com.project.habittracker.R
import com.project.habittracker.util.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment)
                    as NavHostFragment

        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        val session = SessionManager(this)

        if (session.isLogin()) {
            navGraph.setStartDestination(R.id.dashboardFragment)
        }

        navController.graph = navGraph
    }
}