package com.grigoryfedorov.teamwork.data.projects

import com.grigoryfedorov.teamwork.data.projects.datasource.local.ProjectsLocalDataSource
import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectsRepositoryImpl @Inject constructor(
        private val localDataSource: ProjectsLocalDataSource,
        private val remoteDataSource: ProjectsDataSource
) : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        val local = localDataSource.getProjects().filter { projects -> !projects.isEmpty() }
        val remote = remoteDataSource.getProjects().doOnNext { projects -> localDataSource.putProjects(projects) }

        return Observable.concat(local, remote)
    }
}