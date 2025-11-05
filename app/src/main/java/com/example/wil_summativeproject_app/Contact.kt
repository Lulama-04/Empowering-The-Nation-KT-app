package com.example.wil_summativeproject_app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Contact : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // Variables declared in Kotlin
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout._main_contact_us)
        // Hooks for the views
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)
        // Set the Toolbar as the app's action bar
        setSupportActionBar(toolbar)

        // Link the DrawerLayout and the Toolbar
        // This creates the hamburger icon and handles opening/closing the drawer
        navigationView.bringToFront();
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open, // String for accessibility
            R.string.navigation_drawer_close // String for accessibility
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set a listener for navigation item click
        navigationView.setNavigationItemSelectedListener(this)
    }
    // Handle clicks on menu items in the navigation drawer
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, com.example.wil_summativeproject_app.Home::class.java)
                startActivity(intent)
                Toast.makeText(this, "Home Page", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_course -> {
                val intent = Intent(this, Course::class.java)
                startActivity(intent)
                Toast.makeText(this, "Courses Page", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_about -> {
                val intent = Intent(this, About::class.java)
                startActivity(intent)
                Toast.makeText(this, "About Page", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_contact -> {
                Toast.makeText(this, "Contact Page", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_cart -> {
                val intent = Intent(this, Cart::class.java)
                startActivity(intent)
                Toast.makeText(this, "Cart Page", Toast.LENGTH_SHORT).show()
            }

        }
        // Close the drawer when an item is tapped
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
