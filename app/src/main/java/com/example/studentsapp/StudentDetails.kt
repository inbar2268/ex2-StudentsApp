package com.example.studentsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getSerializableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.databinding.ActivityNewStudentBinding
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding
import java.io.Serializable

class StudentDetails : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        student = intent.getSerializableExtra("student_data") as Student?

        student?.let {
            binding.studentIdTextView.text = it.id
            binding.studentNameTextView.text = it.name
            binding.studentAddressTextView.text = it.address
            binding.studentPhoneTextView.text = it.phone
            binding.studentIsChecked.isChecked = it.isChecked
            binding.studentIsChecked.isClickable = false
        }


    }
}