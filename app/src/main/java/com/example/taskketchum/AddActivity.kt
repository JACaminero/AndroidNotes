package com.example.taskketchum

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle/*
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room*/
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.taskketchum.Model.Task
import com.example.taskketchum.fragments.DatePickerFragment
import com.example.taskketchum.data.TaskViewModel

class AddActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        taskViewModel = ViewModelProvider(this@AddActivity).get(TaskViewModel::class.java)

        val bl =intent.extras
        var id=bl?.getString("id")

        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
        findViewById<Button>(R.id.btn_save).setOnClickListener {
            var title =  findViewById<TextView>(R.id.txt_title).text.toString()
            var description =  findViewById<TextView>(R.id.txtDescription).text.toString()
            var date =  findViewById<TextView>(R.id.txt_fecha).text.toString()

            var validationArray = arrayOf(
                title,
                description,
                date
            )
            if (nullValidation(validationArray)){
                var task = Task(0, title, description, date)
                taskViewModel.insert(task)
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, ":(", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun nullValidation(words: Array<String>) : Boolean {
        for (word in words) {
            if (word.isNullOrBlank() || word.isNullOrEmpty()) {
                return false;
            }
        }
        return true
    }

    fun showTimePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val txtDate = findViewById<TextView>(R.id.txt_fecha)
        txtDate.text = "$day-$month-$year"
    }
}
