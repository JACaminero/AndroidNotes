package com.example.taskketchum

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.taskketchum.Model.Task
import com.example.taskketchum.Model.TaskVM
import com.example.taskketchum.data.TaskViewModel
import com.example.taskketchum.fragments.DatePickerFragment

class UpdateActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var task: TaskVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
//        setSupportActionBar(findViewById(R.id.delete_toolbar))

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        task = intent.getSerializableExtra("object") as TaskVM
        fillFields(task.title, task.description, task.date)

        findViewById<Button>(R.id.button2_upd).setOnClickListener{ view ->
            showTimePickerDialog(view)
        }

        findViewById<Button>(R.id.btn_save_upd).setOnClickListener{
            updateTask(task.id)
        }
    }

    private fun fillFields(title: String?, description: String?, date: String?) {
        val txtTitle = findViewById<EditText>(R.id.txt_title_upd)
        val txtDescription = findViewById<EditText>(R.id.txtDescription_upd)
        val txtDate = findViewById<TextView>(R.id.txt_fecha_upd)

        txtTitle.setText(title as String)
        txtDescription.setText(description as String)
        txtDate.text = date as String
    }

    private fun updateTask(taskId: Int) {
        val txtTitle = findViewById<EditText>(R.id.txt_title_upd)
        val txtDescription = findViewById<EditText>(R.id.txtDescription_upd)
        val txtDate = findViewById<TextView>(R.id.txt_fecha_upd)

        var newTask = Task(
            taskId,
            txtTitle.text.toString() as String?,
            txtDescription.text.toString() as String?,
            txtDate.text.toString() as String?
        )
        taskViewModel.update(newTask)
    }

    fun showTimePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val txtDate = findViewById<TextView>(R.id.txt_fecha_upd)
        txtDate.text = "$day-$month-$year"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        return true
    }

    fun deleteTask(id: Int) {
        taskViewModel.delete(id)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_delete -> {
            AlertDialog.Builder(this)
                .setMessage("Seguro k desea eliminar.?")
                .setTitle("Obliterate")
                .setPositiveButton(R.string.yes) { dialog, which ->
                    deleteTask(task.id)
                    Toast.makeText(this, "Terminated Successfully", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(R.string.cancel) { d, w -> }
                .create().show()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}