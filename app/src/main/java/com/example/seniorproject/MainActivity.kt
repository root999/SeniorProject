package com.example.seniorproject


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
    }

    fun setUpNavigation() {
        bottomNavigationView =findViewById(R.id.bttm_nav);
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment?.navController
        bottomNavigationView?.let {
            NavigationUI.setupWithNavController(
                it,
                navHostFragment!!.navController
            )
        }
        if (navController != null) {
            visibilityNavElements(navController)
        }
    }
    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment,
                R.id.registerFragment
                -> bttm_nav?.visibility = View.GONE
                else -> bttm_nav?.visibility = View.VISIBLE
            }
        }
    }
}