package com.example.taskketchum.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskketchum.Model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable
import java.util.*

class TaskViewModel (application: Application) : AndroidViewModel(application), Serializable  {
    private val repository: TaskRepository
    val tasks: LiveData<List<Task>>

    init {
        val taskDao = TaskKetchumDataBase.getDatabase(application, viewModelScope).taskDao()
        repository = TaskRepository(taskDao)
        tasks = repository.tasks
    }

    fun insert(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }

    fun delete(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(id)
    }

    fun update(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(task)
    }
}