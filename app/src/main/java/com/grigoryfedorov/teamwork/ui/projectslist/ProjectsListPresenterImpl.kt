package com.grigoryfedorov.teamwork.ui.projectslist

import android.util.Log
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.interactor.projects.ProjectsInteractor
import com.grigoryfedorov.teamwork.services.resources.ResourceManager
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectsListPresenterImpl(
        private val view: ProjectsListPresenter.View,
        private val projectsInteractor: ProjectsInteractor,
        private val projects: MutableList<Project>,
        private val resourceManager: ResourceManager
) : ProjectsListPresenter {

    private val TAG: String = "ProjectsListPresenter"

    override fun onStart() {
        view.showProgress()
        projectsInteractor.getProjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Project>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(newProjects: List<Project>) {
                        Log.i(TAG, "onNext: $newProjects")
                        projects.clear()
                        projects.addAll(newProjects)

                        view.hideProgress()
                        view.showProjects()
                    }

                    override fun onError(error: Throwable) {
                        Log.i(TAG, "error: $error")

                        view.hideProgress()
                        view.showError(resourceManager.getString(R.string.general_error_message))
                    }

                })
    }
}