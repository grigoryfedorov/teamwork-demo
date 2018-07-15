package com.grigoryfedorov.teamwork.ui.projecttasklist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Task

class ProjectTaskListAdapter(val tasks: List<Task>) : RecyclerView.Adapter<ProjectTaskListAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        viewHolder.content.text = tasks[position].content
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val content: TextView = itemView.findViewById(R.id.task_item_content)

    }

}
