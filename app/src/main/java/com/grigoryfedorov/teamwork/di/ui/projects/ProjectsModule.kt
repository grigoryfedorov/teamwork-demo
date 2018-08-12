package com.grigoryfedorov.teamwork.di.ui.projects

import com.grigoryfedorov.teamwork.ui.projects.ProjectsPresenter
import com.grigoryfedorov.teamwork.ui.projects.ProjectsPresenterImpl
import toothpick.config.Module

class ProjectsModule : Module() {

    init {
        bind(ProjectsPresenter::class.java).to(ProjectsPresenterImpl::class.java)
    }
}
