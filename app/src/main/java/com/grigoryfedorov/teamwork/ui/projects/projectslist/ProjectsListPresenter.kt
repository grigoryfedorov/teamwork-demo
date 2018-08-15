package com.grigoryfedorov.teamwork.ui.projects.projectslist

import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.ui.Presenter

interface ProjectsListPresenter : Presenter {
    interface View {
        fun showProjects(newProjects: List<Project>)
        fun showError(message: String)
        fun hideError()
        fun showProgress()
        fun hideProgress()
        fun showTitle(title: String)
    }

    fun onProjectClick(position: Int)

}