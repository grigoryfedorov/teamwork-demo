package com.grigoryfedorov.teamwork.ui.projecttasklist

import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.interactor.tasks.TasksInteractor
import com.grigoryfedorov.teamwork.services.resources.ResourceManager
import com.grigoryfedorov.teamwork.ui.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProjectTaskListPresenterImpl(
        private val view: ProjectTaskListPresenter.View,
        private val tasksInteractor: TasksInteractor,
        private val projectId: String,
        private val tasks: MutableList<Task>,
        private val resourceManager: ResourceManager,
        private val subscribeOnScheduler: Scheduler = Schedulers.io(),
        private val observeOnScheduler: Scheduler = AndroidSchedulers.mainThread()
) : ProjectTaskListPresenter, BasePresenter() {

    override fun onStart() {
        view.showProgress()

        tasksInteractor.getTasksForProject(projectId)
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


