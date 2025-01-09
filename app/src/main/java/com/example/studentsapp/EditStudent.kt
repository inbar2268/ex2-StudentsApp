package com.example.studentsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.databinding.ActivityEditStudentBinding
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding

class EditStudent : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding
    private var student: Student? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        student = intent.getSerializableExtra("student_data") as Student?

        student?.let {
            binding.editTextId.setText(it.id)
            binding.editTextName.setText(it.name)
            binding.editTextAddress.setText(it.address)
            binding.editTextPhone.setText(it.phone)
            binding.isCkecked.isChecked = it.isChecked
        }

//        binding.saveButton.setOnClickListener{
//
//        }
//        binding.cancelButton.setOnClickListener{
//            finish()
//        }
//        binding.deleteButton.setOnClickListener{
//
//        }
    }
}