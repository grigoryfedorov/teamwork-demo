package com.grigoryfedorov.teamwork.repository

import com.grigoryfedorov.teamwork.domain.Task
import io.reactivex.Observable

interface TasksRepository {
    fun getTasksForProject(projectId: String): Observable<List<Task>>
}