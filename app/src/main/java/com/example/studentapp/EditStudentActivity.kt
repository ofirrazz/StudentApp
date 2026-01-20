package com.example.studentapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    private var pos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        supportActionBar?.title = "Edit Students"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        pos = intent.getIntExtra("pos", -1)
        val student = StudentsRepository.getAt(pos)
        if (student == null) {
            finish()
            return
        }

        val etName = findViewById<EditText>(R.id.etName)
        val etId = findViewById<EditText>(R.id.etId)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val cbChecked = findViewById<CheckBox>(R.id.cbChecked)

        etName.setText(student.name)
        etId.setText(student.id)
        etPhone.setText(student.phone)
        etAddress.setText(student.address)
        cbChecked.isChecked = student.isChecked

        findViewById<Button>(R.id.btnCancel).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            StudentsRepository.deleteAt(pos)
            setResult(RESULT_OK)   // אומר ל-Details: “נמחק”
            finish()
        }


        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val updated = Student(
                name = etName.text.toString(),
                id = etId.text.toString(),
                phone = etPhone.text.toString(),
                address = etAddress.text.toString(),
                isChecked = cbChecked.isChecked
            )
            StudentsRepository.updateAt(pos, updated)

            setResult(RESULT_OK)   // חשוב!
            finish()
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
