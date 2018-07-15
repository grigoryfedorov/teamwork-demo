package com.grigoryfedorov.teamwork.ui.projectslist

interface ProjectsListPresenter {
    interface View {
        fun showProjects()
        fun showError(message: String)
        fun hideError()
        fun showProgress()
        fun hideProgress()
    }

    fun onStart()
}