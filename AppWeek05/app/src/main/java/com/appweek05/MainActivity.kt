package com.appweek05

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // UI component
    private lateinit var buttonAdd: Button
    private lateinit var buttonclear: Button
    private lateinit var editTextStudent: EditText
    private lateinit var textViewCount: TextView
    private lateinit var listView: ListView
    // Collection
    private lateinit var studentList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    // declare variable
    companion object{
        private const val TAG = "KotlinWeek06App"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: AppWeek05 started")

        //function
        setupViews()
        setupListViews()
        setupListeners()
        addInitialData()
    }

    private fun setupViews(){
        listView = findViewById(R.id.listViewStudents)
        editTextStudent = findViewById(R.id.editTextStudent)
        buttonclear = findViewById(R.id.buttonClear)
        buttonAdd = findViewById(R.id.buttonAdd)
        textViewCount = findViewById(R.id.textViewCount)

        studentList = ArrayList()
        Log.d(TAG, "Views initialized")
    }
    private fun setupListViews(){
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, studentList)
        listView.adapter = adapter
        Log.d(TAG, "ListViews and adapter setup completed")
    }
    private fun setupListeners(){
        buttonAdd.setOnClickListener {
            addStudent()
        }
        buttonclear.setOnClickListener {
            clearAllStudents()
        }
        listView.setOnItemLongClickListener { _, _, position, _ -> removeStudent(position)
            true
        }
        listView.setOnItemClickListener { _, _, position, _ ->
            val studentName = studentList[position]
            Toast.makeText(
                this,
                "Selected: $studentName (Position: ${position+1}",
                Toast.LENGTH_SHORT
            ).show()
            Log.d(TAG, "Selected: $studentName at position: $position")
        }
        Log.d(TAG, "Event listeners setup completed")
    }
    private fun addStudent() {
        val studentName = editTextStudent.text.toString().trim()

        if (studentName.isEmpty()) {
            Toast.makeText(this, "Please enter a student name", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Attempted to add empty student name")
            return
        }
        if (studentList.contains(studentName)) {
            Toast.makeText(this, "Student '$studentName' already exists", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Attempted to add duplicate student : $studentName")
            return
        }

        studentList.add(studentName)
        adapter.notifyDataSetChanged()
        editTextStudent.text.clear()
        updateStudentCount()
        Toast.makeText(this, "Added: $studentName", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Added student: $studentName (Total: ${studentList.size})")
    }
    private fun clearAllStudents() {

    }
    private fun removeStudent(position: Int) {

    }

    private fun updateStudentCount() {
        textViewCount.text = "Total Students : ${studentList.size}"
    }
    private fun addInitialData(){
        val initialStudents = listOf("Kim", "Lee", "Park")
        studentList.addAll(initialStudents)
        adapter.notifyDataSetChanged()
        updateStudentCount()
        Log.d(TAG, "Added initial data: $initialStudents")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Current student count: ${studentList.size}")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Saving state with ${studentList.size} students")
    }

}