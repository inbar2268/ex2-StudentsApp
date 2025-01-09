package com.example.studentsapp

import android.annotation.SuppressLint
import android.content.Intent
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

        val studentId = intent.getSerializableExtra("student_id") as String

        studentId?.let {
            Model.shared.getStudent(it) { fetchedStudent ->
                fetchedStudent?.let {
                    student = it
                    binding.editTextId.setText(it.id)
                    binding.editTextName.setText(it.name)
                    binding.editTextAddress.setText(it.address)
                    binding.editTextPhone.setText(it.phone)
                    binding.isCkecked.isChecked = it.isChecked
                }
            }
        }

        binding.saveButton.setOnClickListener{

        }

        binding.cancelButton.setOnClickListener{
            finish()
        }

        binding.deleteButton.setOnClickListener{
            student?.let {
                    it1 -> Model.shared.deleteStudent(it1){
                    val resultIntent = Intent()
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }
}