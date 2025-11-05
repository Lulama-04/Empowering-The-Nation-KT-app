package com.example.wil_summativeproject_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.wil_summativeproject_app.models.CourseInfo

class ChildMinding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_minding)

        val addToCartButton = findViewById<Button>(R.id.add_to_cart_button)
        val backBtn = findViewById<ImageButton>(R.id.backBtn)

        //Add to cart function
        addToCartButton.setOnClickListener {
            CartManager.addCourse(CourseInfo.CHILD_MINDING)
            CartManager.showToast(this, CourseInfo.CHILD_MINDING.name)
        }
        //Back button function
        backBtn.setOnClickListener {
            val intent = Intent(this, Course::class.java)
            startActivity(intent)
        }
    }
}