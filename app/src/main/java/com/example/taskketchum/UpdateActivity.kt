package com.example.taskketchum

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.taskketchum.Model.Task
import com.example.taskketchum.data.TaskViewModel
import com.example.taskketchum.fragments.DatePickerFragment

class UpdateActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private val txtTitle = findViewById<EditText>(R.id.txt_title_upd)
    private val txtDescription = findViewById<EditText>(R.id.txtDescription_upd)
    private val txtDate = findViewById<TextView>(R.id.txt_fecha_upd)
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        var task = intent.getSerializableExtra("object") as Task
        fillFields(task.title, task.description, task.date)

        taskViewModel.update(task)

        findViewById<Button>(R.id.button2_upd).setOnClickListener{view ->
            showTimePickerDialog(view)
        }
    }

    private fun fillFields(title: String?, description: String?, date:String?) {
        txtTitle.setText(title as String)
        txtDescription.setText(description as String)
        txtDate.text = date as String
    }

    private fun updateTask(task: Task) {

    }

    fun showTimePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val txtDate = findViewById<TextView>(R.id.txt_fecha_upd)
        txtDate.text = "$day-$month-$year"
    }
}