package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // דמו כדי שתראי משהו
        if (StudentsRepository.getAll().isEmpty()) {
            StudentsRepository.add(Student("Dana", "123456789", "0500000000", "Tel Aviv", false))
            StudentsRepository.add(Student("Ofir", "987654321", "0521111111", "Ramat Gan", true))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rvStudents)
        val addBtn = findViewById<FloatingActionButton>(R.id.btnAdd)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentsAdapter(
            StudentsRepository.getAll(),
            onRowClick = { pos ->
                // בהמשך נפתח מסך פרטים
            },
            onCheckedChanged = { pos, checked ->
                StudentsRepository.getAt(pos)?.isChecked = checked
            }
        )

        recyclerView.adapter = adapter

        addBtn.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
