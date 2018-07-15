package com.grigoryfedorov.teamwork.data.tasks.datasource.local

import com.grigoryfedorov.teamwork.data.tasks.TasksDataSource
import com.grigoryfedorov.teamwork.domain.Task
import io.reactivex.Observable
import java.util.concurrent.ConcurrentHashMap

class TasksLocalDataSource : TasksDataSource {

    private val tasksByProject: MutableMap<String, List<Task>> = ConcurrentHashMap()

    override fun getTasksForProject(projectId: String): Observable<List<Task>> {
        val projectList = tasksByProject[projectId]

        return if (projectList == null) {
            Observable.just(emptyList())
        } else {
            Observable.just(ArrayList(projectList))
        }
    }

    fun putTasksForProject(projectId: String, tasks: List<Task>) {
        tasksByProject.put(projectId, ArrayList(tasks))
    }
}