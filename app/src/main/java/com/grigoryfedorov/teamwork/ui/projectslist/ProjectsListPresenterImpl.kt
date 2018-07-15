package com.grigoryfedorov.teamwork.ui.projectslist

import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.interactor.projects.ProjectsInteractor
import com.grigoryfedorov.teamwork.services.resources.ResourceManager
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectsListPresenterImpl(
        private val view: ProjectsListPresenter.View,
        private val projectsInteractor: ProjectsInteractor,
        private val projects: MutableList<Project>,
        private val resourceManager: ResourceManager,
        private val subscribeScheduler: Scheduler = Schedulers.io(),
        private val observeScheduler: Scheduler = AndroidSchedulers.mainThread()
) : ProjectsListPresenter {

    override fun onStart() {
        view.showProgress()
        projectsInteractor.getProjects()
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(object : Observer<List<Project>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(newProjects: List<Project>) {
                        projects.clear()
                        projects.addAll(newProjects)

                        view.hideProgress()
                        view.showProjects()
                    }

                    override fun onError(error: Throwable) {
                        view.hideProgress()
                        view.showError(resourceManager.getString(R.string.general_error_message))
                    }

                })
    }
}