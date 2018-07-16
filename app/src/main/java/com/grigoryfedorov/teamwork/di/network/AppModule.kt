package com.grigoryfedorov.teamwork.di.network

import android.app.Application
import com.grigoryfedorov.teamwork.data.projects.ProjectsDataSource
import com.grigoryfedorov.teamwork.data.projects.ProjectsRepositoryImpl
import com.grigoryfedorov.teamwork.data.projects.datasource.remote.ProjectsRemoteDataSource
import com.grigoryfedorov.teamwork.data.tasks.TasksDataSource
import com.grigoryfedorov.teamwork.data.tasks.TasksRepositoryImpl
import com.grigoryfedorov.teamwork.data.tasks.datasource.remote.TasksRemoteDataSource
import com.grigoryfedorov.teamwork.interactor.projects.ProjectsInteractor
import com.grigoryfedorov.teamwork.interactor.projects.ProjectsInteractorImpl
import com.grigoryfedorov.teamwork.interactor.tasks.TasksInteractor
import com.grigoryfedorov.teamwork.interactor.tasks.TasksInteractorImpl
import com.grigoryfedorov.teamwork.network.ApiKeyProvider
import com.grigoryfedorov.teamwork.network.HostProvider
import com.grigoryfedorov.teamwork.network.TeamWorkApiKeyProvider
import com.grigoryfedorov.teamwork.network.TeamWorkProjectsApiHostProvider
import com.grigoryfedorov.teamwork.repository.ProjectsRepository
import com.grigoryfedorov.teamwork.repository.TasksRepository
import com.grigoryfedorov.teamwork.services.resources.ResourceManager
import com.grigoryfedorov.teamwork.services.resources.ResourceManagerImpl
import com.grigoryfedorov.teamwork.ui.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import toothpick.config.Module

class AppModule(application: Application) : Module() {
    init {
        bind(ResourceManager::class.java).toInstance(ResourceManagerImpl(application))
        bind(HostProvider::class.java).to(TeamWorkProjectsApiHostProvider::class.java)
        bind(ApiKeyProvider::class.java).to(TeamWorkApiKeyProvider::class.java)

        bind(Scheduler::class.java).withName(BasePresenter.SUBSCRIBE_ON_SCHEDULER).toInstance(Schedulers.io())
        bind(Scheduler::class.java).withName(BasePresenter.OBSERVE_ON_SCHEDULER).toInstance(AndroidSchedulers.mainThread())

        bind(ProjectsInteractor::class.java).to(ProjectsInteractorImpl::class.java)
        bind(ProjectsRepository::class.java).to(ProjectsRepositoryImpl::class.java)
        bind(ProjectsDataSource::class.java).to(ProjectsRemoteDataSource::class.java)

        bind(TasksInteractor::class.java).to(TasksInteractorImpl::class.java)
        bind(TasksRepository::class.java).to(TasksRepositoryImpl::class.java)
        bind(TasksDataSource::class.java).to(TasksRemoteDataSource::class.java)

    }
}
