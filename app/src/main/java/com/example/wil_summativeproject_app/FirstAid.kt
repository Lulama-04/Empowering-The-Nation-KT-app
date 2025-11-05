package com.example.wil_summativeproject_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.wil_summativeproject_app.models.CourseInfo

class FirstAid : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_aid)

        val addToCartButton = findViewById<Button>(R.id.add_to_cart_button)
        val backBtn = findViewById<ImageButton>(R.id.backBtn)

        //Add to cart button function
        addToCartButton.setOnClickListener {
            CartManager.addCourse(CourseInfo.FIRST_AID)
            CartManager.showToast(this, CourseInfo.FIRST_AID.name)
        }
        //Back button function
        backBtn.setOnClickListener {
            val intent = Intent(this, Course::class.java)
            startActivity(intent)
        }
    }
}