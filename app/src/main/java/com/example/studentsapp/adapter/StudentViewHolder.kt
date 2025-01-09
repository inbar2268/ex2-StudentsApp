package com.example.studentsapp

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.OnItemClickListener
import com.example.studentsapp.R
import com.example.studentsapp.Student
import com.example.studentsapp.databinding.ItemStudentBinding

class StudentViewHolder(private val binding: ItemStudentBinding, listener: OnItemClickListener?) : RecyclerView.ViewHolder(binding.root) {

    private var student: Student? = null

    init {
        // Initialize the listener
        binding.root.setOnClickListener {
            Log.d("TAG", "On click listener on position $adapterPosition")
            listener?.onItemClick(student)  // Call onItemClick when an item is clicked
        }
    }

    fun bind(student: Student, position: Int) {
        // Use the binding to access the views
        this.student = student
        binding.studentName.text = student.name
        binding.studentId.text = student.id
        binding.studentImage.setImageResource(R.drawable.logo) // Default placeholder image
        binding.checkbox.isChecked = student.isChecked

        // Handle checkbox click
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
        }
    }
}