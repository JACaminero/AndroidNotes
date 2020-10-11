package com.example.taskketchum.Model

import androidx.lifecycle.LiveData
import androidx.room.*
import java.io.Serializable


@Entity (tableName = "Task")
data class Task (
    @PrimaryKey(autoGenerate=true) val taskId: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "date") val date: String?,
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE taskId = (:id)")
    fun getById(id: Int): LiveData<Task>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)
}
