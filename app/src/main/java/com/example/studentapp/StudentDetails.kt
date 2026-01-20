package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val pos = intent.getIntExtra("pos", -1)
        if (pos == -1) {
            finish()
            return
        }

        val s = StudentsRepository.getAt(pos)
        if (s == null) {
            finish()
            return
        }

        findViewById<TextView>(R.id.tvName).text = "name: ${s.name}"
        findViewById<TextView>(R.id.tvId).text = "id: ${s.id}"
        findViewById<TextView>(R.id.tvPhone).text = "phone: ${s.phone}"
        findViewById<TextView>(R.id.tvAddress).text = "address: ${s.address}"
        findViewById<CheckBox>(R.id.cbChecked).isChecked = s.isChecked

        findViewById<Button>(R.id.btnEdit).setOnClickListener {
            val i = Intent(this, EditStudentActivity::class.java)
            i.putExtra("pos", pos)
            startActivity(i)
        }
    }
 }

