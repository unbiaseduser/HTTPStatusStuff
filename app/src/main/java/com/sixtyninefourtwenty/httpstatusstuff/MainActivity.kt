package com.sixtyninefourtwenty.httpstatusstuff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.sixtyninefourtwenty.httpstatusstuff.data.repository.PreferencesRepository
import com.sixtyninefourtwenty.httpstatusstuff.databinding.ActivityMainBinding
import com.sixtyninefourtwenty.stuff.asApplication
import com.sixtyninefourtwenty.theming.applyTheming

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: PreferencesRepository
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        applyTheming(
            material2ThemeStyleRes = R.style.Theme_HTTPStatusStuff,
            material3CustomColorsThemeStyleRes = R.style.Theme_HTTPStatusStuff_Material3_Android11,
            material3DynamicColorsThemeStyleRes = R.style.Theme_HTTPStatusStuff_Material3
        )
        super.onCreate(savedInstanceState)

        PreferencesRepository.initDefaultValues(this)
        prefs = asApplication<MyApplication>().prefs
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.graph = navController.navInflater.inflate(R.navigation.mobile_navigation).apply {
            setStartDestination(prefs.initialPage.destResId)
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_dog, R.id.nav_cat, R.id.nav_pizza, R.id.nav_settings), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}