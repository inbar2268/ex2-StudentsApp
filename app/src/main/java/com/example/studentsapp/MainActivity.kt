package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.databinding.ActivityMainBinding

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}


class MainActivity : AppCompatActivity() ,OnItemClickListener {

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
        val adapter = StudentRecyclerAdapter(students)
        adapter.listener = this  // Set the listener for handling item clicks
        binding.recyclerView.adapter = adapter

        binding.btnAddStudent.setOnClickListener {
            val intent = Intent(this, NewStudent::class.java)
            saveStudentActivityResultLauncher.launch(intent)
            Log.d("TAG","finish" )
            }
        }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(student: Student?) {
        student?.let {
            val intent = Intent(this, StudentDetails::class.java)
            intent.putExtra("student_data", it)  // Pass the clicked student data to the next activity
            startActivity(intent)
        }
    }
}




