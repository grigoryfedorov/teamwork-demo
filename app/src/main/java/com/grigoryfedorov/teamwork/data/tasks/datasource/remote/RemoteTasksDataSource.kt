package com.grigoryfedorov.teamwork.data.tasks.datasource.remote

import com.grigoryfedorov.teamwork.data.tasks.TasksDataSource
import com.grigoryfedorov.teamwork.data.tasks.datasource.remote.mapper.TasksEntityMapper
import com.grigoryfedorov.teamwork.data.tasks.datasource.remote.mapper.TasksJsonMapper
import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.network.TeamWorkProjectsApi
import io.reactivex.Observable

class RemoteTasksDataSource(
        var teamWorkProjectsApi: TeamWorkProjectsApi,
        var tasksJsonMapper: TasksJsonMapper,
        var tasksEntityMapper: TasksEntityMapper
) : TasksDataSource {
    override fun getTasksForProject(projectId: String): Observable<List<Task>> {
        return teamWorkProjectsApi.getTasksApiService().getTasksForProject(projectId)
                .map { tasksJsonMapper.map(it) }
                .map { tasksEntityMapper.map(it) }
    }
}