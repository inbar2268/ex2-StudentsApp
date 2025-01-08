package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Initialize binding
    private val students = mutableListOf<Student>()

    private val saveStudentActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d("TAG","here" )
            if (result.resultCode == RESULT_OK) {
                val student = result.data?.getSerializableExtra("student_data") as? Student
                student?.let {
                    students.add(student)
                    binding.recyclerView.adapter?.notifyItemInserted(students.size - 1)

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set the root view

        // Sample students data
        students.add(Student("John Doe", "12345", "122","Israel", true))
        students.add(Student("Jane Smith", "67890","122","Israel", false))

        // Set up RecyclerView with LinearLayoutManager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = StudentAdapter(students)

        binding.btnAddStudent.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            saveStudentActivityResultLauncher.launch(intent)
            Log.d("TAG","finish" )
            }
        }
    }


