package com.grigoryfedorov.teamwork.ui.projects.projectslist

import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.interactor.projects.ProjectsInteractor
import com.grigoryfedorov.teamwork.routing.Router
import com.grigoryfedorov.teamwork.routing.Screen
import com.grigoryfedorov.teamwork.services.projects.ProjectIdHolder
import com.grigoryfedorov.teamwork.services.resources.ResourceManager
import com.grigoryfedorov.teamwork.ui.BasePresenter
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ProjectsListPresenterImpl @Inject constructor(
        private val view: ProjectsListPresenter.View,
        private val projectsInteractor: ProjectsInteractor,
        private val projectIdHolder: ProjectIdHolder,
        private val resourceManager: ResourceManager,
        private val router: Router,
        @Named(SUBSCRIBE_ON_SCHEDULER)
        private val subscribeScheduler: Scheduler,
        @Named(OBSERVE_ON_SCHEDULER)
        private val observeScheduler: Scheduler
) : BasePresenter(), ProjectsListPresenter {

    var projects: List<Project> = emptyList()

    override fun onStart() {
        view.showTitle(resourceManager.getString(R.string.projects_list_title))
        view.showProgress()
        projectsInteractor.getProjects()
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(object : BaseSubscriber<List<Project>>() {

                    override fun onNext(newProjects: List<Project>) {
                        projects = newProjects
                        view.hideProgress()
                        view.hideError()
                        view.showProjects(newProjects)
                    }

                    override fun onError(error: Throwable) {
                        view.hideProgress()
                        view.showError(resourceManager.getString(R.string.general_error_message))
                    }

                })
    }

    override fun onProjectClick(position: Int) {
        projectIdHolder.selectedProject = projects[position]
        router.navigateTo(Screen.PROJECT_TASK_LIST)
    }

}