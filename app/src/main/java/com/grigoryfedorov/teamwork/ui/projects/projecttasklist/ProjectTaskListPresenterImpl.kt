package com.grigoryfedorov.teamwork.ui.projects.projecttasklist

import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.interactor.tasks.TasksInteractor
import com.grigoryfedorov.teamwork.routing.Router
import com.grigoryfedorov.teamwork.services.projects.ProjectIdHolder
import com.grigoryfedorov.teamwork.services.resources.ResourceManager
import com.grigoryfedorov.teamwork.ui.BasePresenter
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ProjectTaskListPresenterImpl @Inject constructor(
        private val view: ProjectTaskListPresenter.View,
        private val tasksInteractor: TasksInteractor,
        private val projectIdHolder: ProjectIdHolder,
        private val resourceManager: ResourceManager,
        private val router: Router,
        @Named(SUBSCRIBE_ON_SCHEDULER)
        private val subscribeOnScheduler: Scheduler,
        @Named(OBSERVE_ON_SCHEDULER)
        private val observeOnScheduler: Scheduler
) : ProjectTaskListPresenter, BasePresenter() {

    override fun onStart() {
        val selectedProject = projectIdHolder.selectedProject

        view.showTitle(selectedProject)
        view.showProgress()

        tasksInteractor.getTasksForProject(selectedProject.id)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(object : BaseSubscriber<List<Task>>() {
                    override fun onNext(newTasks: List<Task>) {
                        view.hideProgress()
                        view.hideError()
                        view.showTasks(newTasks)
                    }

                    override fun onError(error: Throwable) {
                        view.hideProgress()
                        view.showError(resourceManager.getString(R.string.general_error_message))
                    }
                })
    }

    override fun onBackClick() {
        router.back()
    }
}


