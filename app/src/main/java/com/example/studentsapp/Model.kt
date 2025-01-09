package com.example.studentsapp

import android.os.Looper
import androidx.core.os.HandlerCompat
import com.example.studentsapp.dao.AppLocalDb
import com.example.studentsapp.dao.AppLocalDbRepository
import java.util.concurrent.Executors


typealias StudentsCallback = (List<Student>)-> Unit
typealias StudentCallback = (Student)-> Unit
typealias EmptyCallback = ()-> Unit


class Model private constructor(){

    private val database: AppLocalDbRepository = AppLocalDb.database
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())
    private var executor = Executors.newSingleThreadExecutor()


    companion object{
        val shared = Model()

    }

    fun getAllStudents(callback: StudentsCallback) {
        executor.execute{
            val students = database.studentDao().getAllStudents()
            mainHandler.post {
                callback(students)
            }
        }
    }

    fun addStudent(student: Student, callback: EmptyCallback) {
        executor.execute{
            database.studentDao().insertStudent(student)
            mainHandler.post{
                callback()
            }
        }
    }

    fun getStudent(studentId: String, callback: StudentCallback) {
        executor.execute {
            val student = database.studentDao().getStudentById(studentId)  // Assuming `getStudentById` exists in your DAO
            mainHandler.post {
                callback(student)
            }
        }
    }
}