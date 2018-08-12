package com.grigoryfedorov.teamwork.ui.projects.projectslist

import com.grigoryfedorov.teamwork.ui.Presenter

interface ProjectsListPresenter : Presenter {
    interface View {
        fun showProjects()
        fun showError(message: String)
        fun hideError()
        fun showProgress()
        fun hideProgress()
    }

    fun onProjectClick(position: Int)

}