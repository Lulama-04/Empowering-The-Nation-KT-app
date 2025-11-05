package com.example.wil_summativeproject_app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Cart : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    // Views
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var coursesContainer: LinearLayout
    private lateinit var tvSubtotal: TextView
    private lateinit var tvDiscountPercentage: TextView
    private lateinit var tvDiscountAmount: TextView
    private lateinit var tvGrandTotal: TextView
    private lateinit var tvEmptyCart: TextView
    private lateinit var btnClearCart: Button
    private lateinit var btnCheckout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout._main_cart)

        // Initialize views
        initializeViews()

        // Setup toolbar and navigation
        setupToolbarAndNavigation()

        // Display cart
        updateCartView()

        // Setup button listeners
        setupButtonListeners()
    }

    private fun initializeViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)
        coursesContainer = findViewById(R.id.coursesContainer)
        tvSubtotal = findViewById(R.id.tvSubtotal)
        tvDiscountPercentage = findViewById(R.id.tvDiscountPercentage)
        tvDiscountAmount = findViewById(R.id.tvDiscountAmount)
        tvGrandTotal = findViewById(R.id.tvGrandTotal)
        tvEmptyCart = findViewById(R.id.tvEmptyCart)
        btnClearCart = findViewById(R.id.btnClearCart)
        btnCheckout = findViewById(R.id.btnCheckout)
    }

    private fun setupToolbarAndNavigation() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setupButtonListeners() {
        btnClearCart.setOnClickListener {
            if (CartManager.getCartCount() > 0) {
                showClearCartDialog()
            } else {
                Toast.makeText(this, "Cart is already empty", Toast.LENGTH_SHORT).show()
            }
        }

        btnCheckout.setOnClickListener {
            if (CartManager.getCartCount() > 0) {
                showCheckoutDialog()
            } else {
                Toast.makeText(this, "Add courses to cart before checkout", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateCartView() {
        coursesContainer.removeAllViews()

        val cartItems = CartManager.getCartItems()

        if (cartItems.isEmpty()) {
            // Show empty cart message
            tvEmptyCart.visibility = View.VISIBLE
            coursesContainer.visibility = View.GONE
            btnClearCart.isEnabled = false
            btnCheckout.isEnabled = false
        } else {
            // Show cart items
            tvEmptyCart.visibility = View.GONE
            coursesContainer.visibility = View.VISIBLE
            btnClearCart.isEnabled = true
            btnCheckout.isEnabled = true

            // Add each course to the container
            cartItems.forEach { course ->
                addCourseView(course)
            }
        }

        // Update price summary
        updatePriceSummary()
    }

    private fun addCourseView(course: com.example.wil_summativeproject_app.models.CourseInfo) {
        val courseView = layoutInflater.inflate(R.layout.cart_item, coursesContainer, false)

        val courseName = courseView.findViewById<TextView>(R.id.course_name)
        val coursePrice = courseView.findViewById<TextView>(R.id.course_price)
        val courseDuration = courseView.findViewById<TextView>(R.id.course_duration)
        val btnRemove = courseView.findViewById<Button>(R.id.btnRemove)

        courseName.text = course.name
        coursePrice.text = String.format("R %.2f", course.price)
        courseDuration.text = course.duration

        // Remove button listener
        btnRemove.setOnClickListener {
            CartManager.removeCourse(course)
            CartManager.showRemoveToast(this, course.name)
            updateCartView()
        }

        coursesContainer.addView(courseView)
    }

    private fun updatePriceSummary() {
        val subtotal = CartManager.getSubtotal()
        val discountPercentage = CartManager.getDiscountPercentage()
        val discountAmount = CartManager.getDiscountAmount()
        val grandTotal = CartManager.calculateTotalPrice()

        // Display subtotal
        tvSubtotal.text = String.format("R %.2f", subtotal)

        // Display discount (if applicable)
        if (discountPercentage > 0) {
            tvDiscountPercentage.text = String.format("Discount (%d%%)", discountPercentage)
            tvDiscountAmount.text = String.format("-R %.2f", discountAmount)
            tvDiscountPercentage.visibility = View.VISIBLE
            tvDiscountAmount.visibility = View.VISIBLE

            // Highlight discount in green
            tvDiscountAmount.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        } else {
            tvDiscountPercentage.visibility = View.GONE
            tvDiscountAmount.visibility = View.GONE
        }

        // Display grand total (after discount, this is the final price)
        tvGrandTotal.text = String.format("R %.2f", grandTotal)

        // Show discount tip if only 1 course
        if (CartManager.getCartCount() == 1) {
            Toast.makeText(this, "Add 1 more course for 5% discount!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showClearCartDialog() {
        AlertDialog.Builder(this)
            .setTitle("Clear Cart")
            .setMessage("Are you sure you want to remove all courses from your cart?")
            .setPositiveButton("Yes") { _, _ ->
                CartManager.clearCart()
                updateCartView()
                Toast.makeText(this, "Cart cleared", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showCheckoutDialog() {
        val courseCount = CartManager.getCartCount()
        val grandTotal = CartManager.calculateTotalPrice()
        val discountPercentage = CartManager.getDiscountPercentage()

        val message = buildString {
            append("You are enrolling in $courseCount course(s)\n\n")
            if (discountPercentage > 0) {
                append("You saved $discountPercentage%! 🎉\n\n")
            }
            append("Total Amount: R ${String.format("%.2f", grandTotal)}\n\n")
            append("Proceed with enrollment?")
        }

        AlertDialog.Builder(this)
            .setTitle("Complete Enrollment")
            .setMessage(message)
            .setPositiveButton("Enroll Now") { _, _ ->
                Toast.makeText(this, "Enrollment successful! Thank you!", Toast.LENGTH_LONG).show()
                CartManager.clearCart()
                updateCartView()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent = when (item.itemId) {
            R.id.nav_home -> Intent(this, Home::class.java)
            R.id.nav_course -> Intent(this, Course::class.java)
            R.id.nav_about -> Intent(this, About::class.java)
            R.id.nav_contact -> Intent(this, Contact::class.java)
            R.id.nav_cart -> {
                // Already on this page, do nothing
                Toast.makeText(this, "You are already on the Cart page", Toast.LENGTH_SHORT).show()
                null
            }
            else -> null
        }

        if (intent != null) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()
        updateCartView()
    }
}