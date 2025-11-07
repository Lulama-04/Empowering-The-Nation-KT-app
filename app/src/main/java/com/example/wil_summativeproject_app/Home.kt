package com.example.wil_summativeproject_app
import android.content.Intent
import android.annotation.SuppressLint
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
/*Attributes:
* Original code link: https://codingwitht.com/create-a-navigation-drawer-material-design-in-android-studio-tutorial
* creator: CodingwithT
*changes: I used his navigation.drawer code throughtout layouts
*
* Attributes:
* Original code link: https://www.youtube.com/watch?v=1N25p7HigvE&list=PL0Mb_G0vinrrwAdA0kzBiJ_prHZ6TPQHs
* creator: Fichy
*changes: I used his cardview code throughtout layouts with adjustments for the buttons.ect
*/

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // Variables declared in Kotlin
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout._main_home_page)

        // Hooks for the views
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)
        // Hooks for buttons to the courses
        val btnFirstAid = findViewById<Button>(R.id.btnFirstAid)
        val btnLandscaping = findViewById<Button>(R.id.btnLandscaping)
        val btnGardening = findViewById<Button>(R.id.btnGardening)
        val btnCooking = findViewById<Button>(R.id.btnCooking)

        // Set the Toolbar as the app's action bar
        setSupportActionBar(toolbar)

        // Set click listeners for course buttons
        btnLandscaping.setOnClickListener {
            val intent = Intent(this, Landscaping::class.java)
            startActivity(intent)
        }
        btnFirstAid.setOnClickListener {
            val intent = Intent(this, FirstAid::class.java)
            startActivity(intent)
        }
        btnGardening.setOnClickListener {
            val intent = Intent(this, GardenMaintenance::class.java)
            startActivity(intent)
        }
        btnCooking.setOnClickListener {
            val intent = Intent(this, CookingCourse::class.java)
            startActivity(intent)
        }

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

