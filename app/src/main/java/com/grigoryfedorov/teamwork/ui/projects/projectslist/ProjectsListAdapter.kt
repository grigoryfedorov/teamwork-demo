package com.grigoryfedorov.teamwork.ui.projects.projectslist

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.content.res.AppCompatResources
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.grigoryfedorov.teamwork.GlideApp
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Project

class ProjectsListAdapter(
        context: Context,
        private val listener: Listener
) : RecyclerView.Adapter<ProjectsListAdapter.ProjectViewHolder>() {
    private var placeholder: Drawable? = AppCompatResources.getDrawable(context, R.drawable.ic_project_placeholder)
    private var projects: List<Project> = emptyList()

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
                .error(placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .circleCrop()
                .into(viewHolder.logo)

        viewHolder.itemView.setOnClickListener { listener.onProjectClick(position) }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewHolder.itemView.transitionName = null
        }
    }

    override fun getItemCount(): Int = projects.size

    fun updateProjects(newProjects: List<Project>) {
        val diff = DiffUtil.calculateDiff(DiffCallback(projects, newProjects))
        projects = newProjects
        diff.dispatchUpdatesTo(this)
    }

    class ProjectViewHolder(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.project_item_name)
        val logo: ImageView = itemView.findViewById(R.id.project_item_logo)
    }

    interface Listener {
        fun onProjectClick(position: Int)
    }

    class DiffCallback(private val oldProjects: List<Project>,
                       private val newProjects: List<Project>)
        : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldProjects.size

        override fun getNewListSize(): Int = newProjects.size

        override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
            return oldProjects[p0] == newProjects[p1]
        }

        override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
            return oldProjects[p0].id == newProjects[p1].id
        }
    }

}
