package com.example.studentsapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    val phone: String,
    val address: String,
    var isChecked: Boolean
)