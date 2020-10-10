package com.example.taskketchum.database

import com.example.taskketchum.Model.Task
import com.example.taskketchum.Model.TaskDao
import androidx.lifecycle.LiveData as LiveData1

class TaskRepository(private val taskDao: TaskDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val tasks: LiveData1<List<Task>> = taskDao.getAll()

    suspend fun getById(id: Int) : LiveData1<Task> {
        return taskDao.getById(id)
    }

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }
}
