package com.grigoryfedorov.teamwork.di.ui.projects.projecttasklist

import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.ui.projects.projecttasklist.ProjectTaskListPresenter
import com.grigoryfedorov.teamwork.ui.projects.projecttasklist.ProjectTaskListPresenterImpl
import toothpick.config.Module

class ProjectTaskListActivityModule(
        view: ProjectTaskListPresenter.View,
        tasks: MutableList<Task>) : Module() {
    init {
        bind(ProjectTaskListPresenter.View::class.java).toInstance(view)
        bind(MutableList::class.java).toInstance(tasks)
        bind(ProjectTaskListPresenter::class.java).to(ProjectTaskListPresenterImpl::class.java)
    }
}
