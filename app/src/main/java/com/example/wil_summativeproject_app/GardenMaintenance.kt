package com.example.wil_summativeproject_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.wil_summativeproject_app.models.CourseInfo

class GardenMaintenance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garden_maintenance)

        val addToCartButton = findViewById<Button>(R.id.add_to_cart_button)
        val backBtn = findViewById<ImageButton>(R.id.backBtn)

        addToCartButton.setOnClickListener {
            CartManager.addCourse(CourseInfo.GARDEN_MAINTENANCE)
            CartManager.showToast(this, CourseInfo.GARDEN_MAINTENANCE.name)
        }
        //Back button function
        backBtn.setOnClickListener {
            val intent = Intent(this, Course::class.java)
            startActivity(intent)
        }
    }
}