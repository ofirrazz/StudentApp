package com.example.studentapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentsAdapter(
    private val students: MutableList<Student>,
    private val onRowClick: (Int) -> Unit,
    private val onCheckedChanged: (Int, Boolean) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.StudentVH>() {

    class StudentVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.txtName)
        val id: TextView = itemView.findViewById(R.id.txtId)
        val check: CheckBox = itemView.findViewById(R.id.chkChecked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentVH(view)
    }

    override fun getItemCount() = students.size

    override fun onBindViewHolder(holder: StudentVH, position: Int) {
        val s = students[position]
        holder.name.text = s.name
        holder.id.text = s.id

        holder.check.setOnCheckedChangeListener(null)
        holder.check.isChecked = s.isChecked
        holder.check.setOnCheckedChangeListener { _, checked ->
            onCheckedChanged(position, checked)
        }

        holder.itemView.setOnClickListener {
            onRowClick(position)
        }
    }
}
