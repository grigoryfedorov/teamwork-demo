package com.grigoryfedorov.teamwork.data.tasks

import com.grigoryfedorov.teamwork.domain.Task
import io.reactivex.Observable

interface TasksDataSource {
    fun getTasksForProject(projectId: String): Observable<List<Task>>
}