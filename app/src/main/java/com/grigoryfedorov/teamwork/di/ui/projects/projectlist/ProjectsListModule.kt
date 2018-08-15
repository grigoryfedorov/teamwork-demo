package com.grigoryfedorov.teamwork.di.ui.projects.projectlist

import com.grigoryfedorov.teamwork.ui.projects.projectslist.ProjectsListPresenter
import com.grigoryfedorov.teamwork.ui.projects.projectslist.ProjectsListPresenterImpl
import toothpick.config.Module

class ProjectsListModule(
        view: ProjectsListPresenter.View
) : Module() {
    init {
        bind(ProjectsListPresenter.View::class.java).toInstance(view)
        bind(ProjectsListPresenter::class.java).to(ProjectsListPresenterImpl::class.java)
    }
}