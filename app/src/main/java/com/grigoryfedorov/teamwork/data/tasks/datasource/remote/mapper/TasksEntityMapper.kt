package com.grigoryfedorov.teamwork.data.tasks.datasource.remote.mapper

import com.grigoryfedorov.teamwork.data.Mapper
import com.grigoryfedorov.teamwork.data.tasks.datasource.remote.model.ApiTask
import com.grigoryfedorov.teamwork.domain.Task

class TasksEntityMapper : Mapper<List<ApiTask>, List<Task>> {
    override fun map(srcModel: List<ApiTask>): List<Task> {
        val tasks = ArrayList<Task>(srcModel.size)

        for (apiTask in srcModel) {
            tasks.add(Task(id = apiTask.id, content = apiTask.content))
        }

        return tasks
    }

}
