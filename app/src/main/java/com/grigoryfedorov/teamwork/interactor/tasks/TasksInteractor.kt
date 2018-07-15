package com.grigoryfedorov.teamwork.interactor.tasks

import com.grigoryfedorov.teamwork.domain.Task
import io.reactivex.Observable

interface TasksInteractor {
    fun getTasksForProject(projectId: String): Observable<List<Task>>
}