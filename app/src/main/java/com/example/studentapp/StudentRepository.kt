package com.example.studentapp

object StudentsRepository {
    private val students = mutableListOf<Student>()

    fun getAll() = students

    fun add(student: Student) {
        students.add(student)
    }

    fun getAt(position: Int): Student? =
        students.getOrNull(position)

    fun updateAt(position: Int, student: Student) {
        if (position in students.indices)
            students[position] = student
    }

    fun deleteAt(position: Int) {
        if (position in students.indices)
            students.removeAt(position)
    }
}
