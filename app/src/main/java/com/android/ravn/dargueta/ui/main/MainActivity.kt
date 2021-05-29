package com.android.ravn.dargueta.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.android.ravn.dargueta.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment)
            .let { navHost ->
                val navController = navHost.navController
                val config = AppBarConfiguration(navController.graph)
                findViewById<Toolbar>(R.id.toolbar_main)
                    .setupWithNavController(navController, config)
            }
    }
}
