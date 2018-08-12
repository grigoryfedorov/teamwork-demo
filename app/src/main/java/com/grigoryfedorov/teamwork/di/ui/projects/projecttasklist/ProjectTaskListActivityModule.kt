package com.grigoryfedorov.teamwork.di.ui.projects.projecttasklist

import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.ui.projects.projecttasklist.ProjectTaskListPresenter
import com.grigoryfedorov.teamwork.ui.projects.projecttasklist.ProjectTaskListPresenterImpl
import toothpick.config.Module

class ProjectTaskListActivityModule(
        view: ProjectTaskListPresenter.View,
        projectId: String,
        tasks: MutableList<Task>) : Module() {
    init {
        bind(ProjectTaskListPresenter.View::class.java).toInstance(view)
        bind(String::class.java).toInstance(projectId)
        bind(MutableList::class.java).toInstance(tasks)
        bind(ProjectTaskListPresenter::class.java).to(ProjectTaskListPresenterImpl::class.java)
    }
}
