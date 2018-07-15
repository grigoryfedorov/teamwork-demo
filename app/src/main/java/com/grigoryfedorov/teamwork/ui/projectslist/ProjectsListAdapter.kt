package com.grigoryfedorov.teamwork.ui.projectslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Project

class ProjectsListAdapter(private val projects: List<Project>) : RecyclerView.Adapter<ProjectsListAdapter.ProjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.project_item, parent, false)
        return ProjectViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ProjectViewHolder, position: Int) {
        viewHolder.name.text = projects[position].name
    }

    override fun getItemCount(): Int = projects.size

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.project_item_name)

    }

}
