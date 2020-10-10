package com.example.taskketchum.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskketchum.Model.Task
import com.example.taskketchum.R
import kotlinx.android.synthetic.main.row_layout.view.*

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private var taskList = emptyList<Task>()
    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.row_layout, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.itemView.tv_title.text = currentItem.title.toString()
        holder.itemView.tv_date.text = currentItem.date.toString()
        holder.itemView.tv_id.text = currentItem.taskId.toString()

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(tasks: List<Task>) {
        this.taskList = tasks
        notifyDataSetChanged()
    }
}