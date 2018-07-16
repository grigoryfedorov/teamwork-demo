package com.grigoryfedorov.teamwork.interactor.tasks

import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.repository.TasksRepository
import io.reactivex.Observable
import javax.inject.Inject

class TasksInteractorImpl @Inject constructor(
        private val tasksRepository: TasksRepository
) : TasksInteractor {
    override fun getTasksForProject(projectId: String): Observable<List<Task>> = tasksRepository.getTasksForProject(projectId)

}