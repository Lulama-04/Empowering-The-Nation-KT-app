package com.example.wil_summativeproject_app.models

// Data class representing a course
data class CourseInfo(
    val id: String,
    val name: String,
    val price: Double,
    val duration: String,
    val description: String
) {
    companion object {
        // Six-month courses
        val FIRST_AID = CourseInfo(
            "first_aid",
            "First Aid",
            1500.0,
            "6 months",
            "To provide first aid awareness and basic life support"
        )

        val SEWING = CourseInfo(
            "sewing",
            "Sewing",
            1500.0,
            "6 months",
            "To provide alterations and new garment design"
        )

        val LANDSCAPING = CourseInfo(
            "landscaping",
            "Landscaping",
            1500.0,
            "6 months",
            "To provide landscaping services for new and established gardens"
        )

        val LIFE_SKILLS = CourseInfo(
            "life_skills",
            "Life Skills",
            1500.0,
            "6 months",
            "To provide skills to navigate basic life necessities"
        )

        // Six-week courses
        val CHILD_MINDING = CourseInfo(
            "child_minding",
            "Child Minding",
            750.0,
            "6 weeks",
            "To provide basic child and baby care"
        )

        val COOKING = CourseInfo(
            "cooking",
            "Cooking",
            750.0,
            "6 weeks",
            "To prepare and cook nutritious family meals"
        )

        val GARDEN_MAINTENANCE = CourseInfo(
            "garden_maintenance",
            "Garden Maintenance",
            750.0,
            "6 weeks",
            "To provide basic knowledge of watering, pruning and weeding"
        )
    }
}