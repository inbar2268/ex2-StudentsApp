package com.example.studentsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.databinding.ItemStudentBinding

class StudentAdapter(private val students: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        // Inflate the item layout using ViewBinding
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int = students.size

    inner class StudentViewHolder(private val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            // Use the binding to access the views
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
}
