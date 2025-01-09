package com.example.studentsapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.databinding.ItemStudentBinding


class StudentRecyclerAdapter(private val students: List<Student>) :

    RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder>() {
    var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(
            student = students.get(position),
            position = position
        )
    }

    override fun getItemCount(): Int = students.size ?: 0

    inner class StudentViewHolder(private val binding: ItemStudentBinding, listener: OnItemClickListener?) : RecyclerView.ViewHolder(binding.root) {

        init {
            // Initialize the listener
            binding.root.setOnClickListener {
                Log.d("TAG", "On click listener on position $adapterPosition")
                listener?.onItemClick(students[adapterPosition])  // Call onItemClick when an item is clicked
            }
        }

        fun bind(student: Student, position: Int) {
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
