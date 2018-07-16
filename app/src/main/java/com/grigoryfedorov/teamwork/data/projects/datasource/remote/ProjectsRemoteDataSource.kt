package com.grigoryfedorov.teamwork.data.projects.datasource.remote

import com.grigoryfedorov.teamwork.data.projects.ProjectsDataSource
import com.grigoryfedorov.teamwork.data.projects.datasource.remote.mapper.ProjectsEntityMapper
import com.grigoryfedorov.teamwork.data.projects.datasource.remote.mapper.ProjectsJsonMapper
import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.network.TeamWorkProjectsApi
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteDataSource @Inject constructor(
        var teamWorkProjectsApi: TeamWorkProjectsApi,
        var projectsJsonMapper: ProjectsJsonMapper,
        var projectsEntityEntityMapper: ProjectsEntityMapper
) : ProjectsDataSource {

    override fun getProjects(): Observable<List<Project>> {
        return teamWorkProjectsApi.getProjectsApiService()
                .getProjects().map { projectsJsonMapper.map(it) }
                .map { projectsEntityEntityMapper.map(it) }
    }
}