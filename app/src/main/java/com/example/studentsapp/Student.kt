package com.example.studentsapp

import java.io.Serializable

data class Student(
    val id: String,
    val name: String,
    val phone: String,
    val address: String,
    var isChecked: Boolean
) : Serializable