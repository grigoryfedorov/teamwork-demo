package com.grigoryfedorov.teamwork.ui.projects.projecttasklist

import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.ui.Presenter

interface ProjectTaskListPresenter : Presenter {
    interface View {
        fun showTasks(newTasks: List<Task>)
        fun showError(message: String)
        fun hideError()
        fun showProgress()
        fun hideProgress()
        fun showTitle(project: Project)
    }

    fun onBackClick()

}
