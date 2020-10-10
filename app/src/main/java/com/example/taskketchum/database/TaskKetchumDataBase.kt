package com.example.taskketchum.database

import com.example.taskketchum.Model.Task
import com.example.taskketchum.Model.TaskDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Task::class], version = 1)
abstract class TaskKetchumDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        // Singleton prevents multiple instances of com.example.taskketchum.database opening at the
        // same time.
        @Volatile
        var INSTANCE: TaskKetchumDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TaskKetchumDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    TaskKetchumDataBase::class.java,
                    "task_ketchum_database"
                )
//                .addCallback(TaskKetchumDatabaseCallback(scope))
                .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

private class TaskKetchumDatabaseCallback(
    private val scope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        TaskKetchumDataBase.INSTANCE?.let { database ->
            scope.launch {
                populateDatabase(database.taskDao())
            }
        }
    }

    suspend fun populateDatabase(taskDao: TaskDao) {
        // Delete all content here.
//        wordDao.deleteAll()

        var task = Task(1, "Hacer la tarea", "LA TAREA MANITO", "ll" )
        taskDao.insert(task)
    }
}