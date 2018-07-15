package com.grigoryfedorov.teamwork.data.projects

import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.network.TeamWorkProjectsApi
import io.reactivex.Observable

class ProjectsRemoteDataSource(
        var teamWorkProjectsApi: TeamWorkProjectsApi,
        var projectsJsonMapper: ProjectsJsonMapper,
        var projectsEntityMapper: ProjectsEntityMapper
) : ProjectsDataSource {


    override fun getProjects(): Observable<List<Project>> {
        return teamWorkProjectsApi.getProjectsApiService()
                .getProjects().map { projectsJsonMapper.map(it) }
                .map { projectsEntityMapper.map(it) }
    }
}