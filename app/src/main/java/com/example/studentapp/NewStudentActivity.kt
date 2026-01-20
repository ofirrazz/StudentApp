package com.example.studentapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val etName = findViewById<EditText>(R.id.etName)
        val etId = findViewById<EditText>(R.id.etId)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val cbChecked = findViewById<CheckBox>(R.id.cbChecked)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener { finish() }


        btnSave.setOnClickListener {
            val student = Student(
                name = etName.text.toString(),
                id = etId.text.toString(),
                phone = etPhone.text.toString(),
                address = etAddress.text.toString(),
                isChecked = cbChecked.isChecked
            )

            StudentsRepository.add(student)
            finish() // חזרה לרשימה
        }
    }
}
