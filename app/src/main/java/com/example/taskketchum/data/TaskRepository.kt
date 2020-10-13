package com.example.taskketchum.data

import com.example.taskketchum.Model.Task
import com.example.taskketchum.Model.TaskDao
import androidx.lifecycle.LiveData as LiveData1

class TaskRepository(private val taskDao: TaskDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val tasks: LiveData1<List<Task>> = taskDao.getAll()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun delete(id: Int) {
        taskDao.delete(id)
    }

    suspend fun update(task:Task) {
        taskDao.update(task)
    }
}
