package vn.edu.listexamples

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
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: MutableList<StudentModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_student_layout)

        val editName = findViewById<EditText>(R.id.editName)
        val editMSSV = findViewById<EditText>(R.id.editMSSV)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val listView = findViewById<ListView>(R.id.list_students)

        studentList = mutableListOf()
        studentAdapter = StudentAdapter(this, studentList)
        listView.adapter = studentAdapter

        btnAdd.setOnClickListener {
            val name = editName.text.toString().trim()
            val mssv = editMSSV.text.toString().trim()

            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                val newStudent = StudentModel(name, mssv)
                studentList.add(0, newStudent) // Thêm vào đầu danh sách
                studentAdapter.notifyDataSetChanged()
                editName.text.clear()
                editMSSV.text.clear()
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
