package com.grigoryfedorov.teamwork.di.ui.projects.projecttasklist

import com.grigoryfedorov.teamwork.ui.projects.projecttasklist.ProjectTaskListPresenter
import com.grigoryfedorov.teamwork.ui.projects.projecttasklist.ProjectTaskListPresenterImpl
import toothpick.config.Module

class ProjectTaskListActivityModule(
        view: ProjectTaskListPresenter.View
) : Module() {
    init {
        bind(ProjectTaskListPresenter.View::class.java).toInstance(view)
        bind(ProjectTaskListPresenter::class.java).to(ProjectTaskListPresenterImpl::class.java)
    }
}
