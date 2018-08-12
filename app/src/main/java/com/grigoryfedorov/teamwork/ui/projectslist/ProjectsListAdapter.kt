package com.grigoryfedorov.teamwork.ui.projectslist

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.grigoryfedorov.teamwork.GlideApp
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Project

class ProjectsListAdapter(
        context: Context,
        private val projects: List<Project>,
        private val listener: Listener
) : RecyclerView.Adapter<ProjectsListAdapter.ProjectViewHolder>() {
    private var placeholder: Drawable? = AppCompatResources.getDrawable(context, R.drawable.ic_project_placeholder)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.project_item, parent, false)
        return ProjectViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        viewHolder.name.text = project.name

        GlideApp.with(viewHolder.itemView)
                .load(project.logo)
                .placeholder(placeholder)
                .fallback(placeholder)
                .circleCrop()
                .into(viewHolder.logo)

        viewHolder.itemView.setOnClickListener { listener.onProjectClick(position) }
    }

    override fun getItemCount(): Int = projects.size

    class ProjectViewHolder(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.project_item_name)
        val logo: ImageView = itemView.findViewById(R.id.project_item_logo)
    }

    interface Listener {
        fun onProjectClick(position: Int)
    }

}
