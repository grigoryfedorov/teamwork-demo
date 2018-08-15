package com.grigoryfedorov.teamwork.ui.projects.projecttasklist

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Task

class ProjectTaskListAdapter : RecyclerView.Adapter<ProjectTaskListAdapter.TaskViewHolder>() {

    var tasks: List<Task> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        viewHolder.content.text = tasks[position].content
    }

    fun updateTasks(newTasks: List<Task>) {
        val diff = DiffUtil.calculateDiff(DiffCallback(tasks, newTasks))

        tasks = newTasks

        diff.dispatchUpdatesTo(this)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val content: TextView = itemView.findViewById(R.id.task_item_content)
    }

    class DiffCallback(private val oldTasks: List<Task>,
                       private val newTasks: List<Task>)
        : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldTasks.size

        override fun getNewListSize(): Int = newTasks.size

        override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
            return oldTasks[p0] == newTasks[p1]
        }

        override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
            return oldTasks[p0].id == newTasks[p1].id
        }
    }

}
