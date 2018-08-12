package com.grigoryfedorov.teamwork.di.ui.projects.projectlist

import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.ui.projects.projectslist.ProjectsListPresenter
import com.grigoryfedorov.teamwork.ui.projects.projectslist.ProjectsListPresenterImpl
import toothpick.config.Module

class ProjectsListModule(
        view: ProjectsListPresenter.View,
        projects: MutableList<Project>
) : Module() {
    init {
        bind(ProjectsListPresenter.View::class.java).toInstance(view)
        bind(MutableList::class.java).toInstance(projects)
        bind(ProjectsListPresenter::class.java).to(ProjectsListPresenterImpl::class.java)
    }
}