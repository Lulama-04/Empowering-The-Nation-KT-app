package com.example.wil_summativeproject_app

import android.content.Context
import android.widget.Toast
import com.example.wil_summativeproject_app.models.CourseInfo

object CartManager {
    private val cart = mutableListOf<CourseInfo>()

    // Add a course to the cart
    fun addCourse(course: CourseInfo) {
        if (!cart.contains(course)) {
            cart.add(course)
        }
    }

    // Remove a course from the cart
    fun removeCourse(course: CourseInfo) {
        cart.remove(course)
    }

    // Check if course is already in cart
    fun isCourseInCart(course: CourseInfo): Boolean {
        return cart.contains(course)
    }

    // Get all courses in the cart
    fun getCartItems(): List<CourseInfo> {
        return cart.toList()
    }

    // Get cart item count
    fun getCartCount(): Int {
        return cart.size
    }

    // Clear the cart
    fun clearCart() {
        cart.clear()
    }

    // Calculate subtotal (before discount)
    fun getSubtotal(): Double {
        return cart.sumOf { it.price }
    }

    // Get discount percentage based on number of courses
    // 2 courses = 5%, 3 courses = 10%, 4+ courses = 15%
    fun getDiscountPercentage(): Int {
        return when (cart.size) {
            2 -> 5
            3 -> 10
            in 4..Int.MAX_VALUE -> 15
            else -> 0
        }
    }

    // Calculate discount amount
    fun getDiscountAmount(): Double {
        val subtotal = getSubtotal()
        val discountPercentage = getDiscountPercentage()
        return subtotal * (discountPercentage / 100.0)
    }

    // Calculate total after discount
    fun calculateTotalPrice(): Double {
        return getSubtotal() - getDiscountAmount()
    }

    // Calculate VAT (15%)
    fun getVATAmount(): Double {
        return calculateTotalPrice() * 0.15
    }

    // Get grand total (with VAT)
    fun getGrandTotal(): Double {
        return calculateTotalPrice() + getVATAmount()
    }

    // Notify the user what has been added to the cart
    fun showToast(context: Context, courseName: String) {
        Toast.makeText(context, "$courseName has been added to the cart", Toast.LENGTH_SHORT).show()
    }

    // Show remove toast
    fun showRemoveToast(context: Context, courseName: String) {
        Toast.makeText(context, "$courseName has been removed from the cart", Toast.LENGTH_SHORT).show()
    }
}