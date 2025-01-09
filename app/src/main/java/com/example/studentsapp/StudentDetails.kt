package com.example.studentsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentId = intent.getSerializableExtra("student_id") as String

        studentId?.let {
            Model.shared.getStudent(it) { fetchedStudent ->
                fetchedStudent?.let {
                    student = it
                    binding.studentIdTextView.text = "Id: ${it.id}"
                    binding.studentNameTextView.text = "Name: ${it.name}"
                    binding.studentAddressTextView.text = "Address: ${it.address}"
                    binding.studentPhoneTextView.text = "Phone: ${it.phone}"
                    binding.studentIsChecked.isChecked = it.isChecked
                    binding.studentIsChecked.isClickable = false
                }
            }
        }

        binding.editStudentButton.setOnClickListener{
            val intent = Intent(this, EditStudent::class.java)
            intent.putExtra("student_id", studentId)  // Pass the clicked student data to the next activity
            startActivity(intent)
        }
    }
}