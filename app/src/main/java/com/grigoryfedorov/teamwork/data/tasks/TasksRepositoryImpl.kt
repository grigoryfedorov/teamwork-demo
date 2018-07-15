package com.grigoryfedorov.teamwork.data.tasks

import com.grigoryfedorov.teamwork.data.tasks.datasource.local.TasksLocalDataSource
import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.repository.TasksRepository
import io.reactivex.Observable

class TasksRepositoryImpl(
        private val localTasksDataSource: TasksLocalDataSource,
        private val remoteTasksDataSource: TasksDataSource
) : TasksRepository {
    override fun getTasksForProject(projectId: String): Observable<List<Task>> {
        val local = localTasksDataSource.getTasksForProject(projectId)
                .filter { tasks -> !tasks.isEmpty() }

        val remote = remoteTasksDataSource.getTasksForProject(projectId)
                .doOnNext { tasks -> localTasksDataSource.putTasksForProject(projectId, tasks) }

        return Observable.concat(local, remote)
    }
}