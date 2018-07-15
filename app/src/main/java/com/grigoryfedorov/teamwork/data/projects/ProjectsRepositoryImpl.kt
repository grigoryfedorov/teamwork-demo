package com.grigoryfedorov.teamwork.data.projects

import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.repository.ProjectsRepository
import io.reactivex.Observable

class ProjectsRepositoryImpl(
        private val remoteDataSource: ProjectsDataSource
) : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return remoteDataSource.getProjects()
    }
}