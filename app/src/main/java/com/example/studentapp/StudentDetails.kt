package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {

    private var pos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        pos = intent.getIntExtra("pos", -1)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvId = findViewById<TextView>(R.id.tvId)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)
        val cbChecked = findViewById<CheckBox>(R.id.cbChecked)
        val btnEdit = findViewById<Button>(R.id.btnEdit)

        val s = StudentsRepository.getAt(pos)
        if (s == null) {
            finish()
            return
        }

        tvName.text = "name: ${s.name}"
        tvId.text = "id: ${s.id}"
        tvPhone.text = "phone: ${s.phone}"
        tvAddress.text = "address: ${s.address}"
        cbChecked.isChecked = s.isChecked

        btnEdit.setOnClickListener {
            val i = Intent(this, EditStudentActivity::class.java)
            i.putExtra("pos", pos)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        // אם ערכו/מחקו, נרענן פרטים
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
    }
}
