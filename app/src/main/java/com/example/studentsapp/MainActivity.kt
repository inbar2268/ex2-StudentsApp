package com.example.studentsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Initialize binding

    private val students = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set the root view

        // Sample students data
        students.add(Student("John Doe", "12345", "122","Israel", true))
        students.add(Student("Jane Smith", "67890","122","Israel", false))

        // Set up RecyclerView with LinearLayoutManager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = StudentAdapter(students)

        // Add Student button click listener
        binding.btnAddStudent.setOnClickListener {
            students.add(Student("New Student", "11223", "122","Israel", true))
            binding.recyclerView.adapter?.notifyItemInserted(students.size - 1)
        }
    }
}
