package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.StudentAdapter
import com.example.myapplication.StudentModel

class MainActivityAddStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_student_layout)

        val students = mutableListOf<StudentModel>()
        repeat(28) {
            students.add(StudentModel(
                resources.getIdentifier("thumb$it", "drawable", packageName),
                "Student $it",
                "SV$it"
            ))
        }

        val adapter = StudentAdapter(students)

        val listStudents = findViewById<ListView>(R.id.list_students)
        listStudents.adapter = adapter
        val buttonDelete = findViewById<Button>(R.id.btnAdd)
        buttonDelete.setOnClickListener {
            adapter.removeSelectedStudents()
            Toast.makeText(this, "Đã xóa sinh viên được chọn", Toast.LENGTH_SHORT).show()
        }
    }
}
