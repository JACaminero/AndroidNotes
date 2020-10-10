package com.example.taskketchum

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskketchum.database.TaskViewModel
import com.example.taskketchum.fragments.TaskListAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//       appbar
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val recView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recView.layoutManager = layoutManager

        val adapter = TaskListAdapter()
        recView.adapter = adapter

        taskViewModel = ViewModelProvider(this@MainActivity).get(TaskViewModel::class.java)
        taskViewModel.tasks.observe(this, Observer { task -> adapter.setData(task) })
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add -> {
            val intent = Intent(this@MainActivity, AddActivity::class.java) //Optional parameters
            this@MainActivity.startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}