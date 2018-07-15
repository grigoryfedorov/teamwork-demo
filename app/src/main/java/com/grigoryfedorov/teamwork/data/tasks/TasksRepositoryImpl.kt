package com.grigoryfedorov.teamwork.data.tasks

import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.repository.TasksRepository
import io.reactivex.Observable

class TasksRepositoryImpl(
        private val remoteTasksDataSource: TasksDataSource
) : TasksRepository {
    override fun getTasksForProject(projectId: String): Observable<List<Task>> {
        return remoteTasksDataSource.getTasksForProject(projectId)
    }
}