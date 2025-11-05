package com.example.wil_summativeproject_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

// Variables declared in Kotlin
private lateinit var drawerLayout: DrawerLayout
private lateinit var navigationView: NavigationView
@SuppressLint("StaticFieldLeak")
private lateinit var toolbar: Toolbar

class Course : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout._main_courses_page)
        // Hooks for the views
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Hooks for the buttons
        val btnChildMinding = findViewById<Button>(R.id.btnChildMinding)
        val btnCooking = findViewById<Button>(R.id.btnCooking)
        val btnGardening = findViewById<Button>(R.id.btnGardening)
        val btnFirstAid = findViewById<Button>(R.id.btnFirstAid)
        val btnLifeSkills = findViewById<Button>(R.id.btnLifeSkills)
        val btnSewing = findViewById<Button>(R.id.btnSewing)
        val btnLandscaping = findViewById<Button>(R.id.btnLandscaping)

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

        //buttons for each course
        btnChildMinding.setOnClickListener {
            val intent = Intent(this, ChildMinding::class.java)
            startActivity(intent)
        }
        btnCooking.setOnClickListener {
            val intent = Intent(this, CookingCourse::class.java)
            startActivity(intent)
        }
        btnGardening.setOnClickListener {
            val intent = Intent(this, GardenMaintenance::class.java)
            startActivity(intent)
        }
        btnFirstAid.setOnClickListener {
            val intent = Intent(this, FirstAid::class.java)
            startActivity(intent)
        }
        btnLifeSkills.setOnClickListener {
            val intent = Intent(this, LifeSkills::class.java)
            startActivity(intent)
        }
        btnSewing.setOnClickListener {
            val intent = Intent(this, Sewing::class.java)
            startActivity(intent)
        }
        btnLandscaping.setOnClickListener {
            val intent = Intent(this, Landscaping::class.java)
            startActivity(intent)
        }
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
                Toast.makeText(this, "Courses Page", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_about -> {
                val intent = Intent(this, About::class.java)
                startActivity(intent)
                Toast.makeText(this, "About Page", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_contact -> {
                val intent = Intent(this, Contact::class.java)
                startActivity(intent)
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

    // Close the drawer if it's open when the back button is pressed
    @SuppressLint("GestureBackNavigation")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
