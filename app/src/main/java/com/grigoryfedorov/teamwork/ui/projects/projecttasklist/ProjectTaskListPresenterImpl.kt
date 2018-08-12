package com.grigoryfedorov.teamwork.ui.projects.projecttasklist

import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.interactor.tasks.TasksInteractor
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
        private val tasks: MutableList<Task>,
        private val resourceManager: ResourceManager,
        @Named(SUBSCRIBE_ON_SCHEDULER)
        private val subscribeOnScheduler: Scheduler,
        @Named(OBSERVE_ON_SCHEDULER)
        private val observeOnScheduler: Scheduler
) : ProjectTaskListPresenter, BasePresenter() {

    override fun onStart() {
        view.showProgress()

        tasksInteractor.getTasksForProject(projectIdHolder.selectedProjectId)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(object : BaseSubscriber<List<Task>>() {
                    override fun onNext(newTasks: List<Task>) {
                        tasks.clear()
                        tasks.addAll(newTasks)

                        view.hideProgress()
                        view.hideError()
                        view.showTasks()
                    }

                    override fun onError(error: Throwable) {
                        view.hideProgress()
                        view.showError(resourceManager.getString(R.string.general_error_message))
                    }
                })
    }
}


