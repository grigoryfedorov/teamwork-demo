package com.grigoryfedorov.teamwork.data.tasks.datasource.remote.mapper

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.grigoryfedorov.teamwork.data.Mapper
import com.grigoryfedorov.teamwork.data.tasks.datasource.remote.model.ApiTask
import com.grigoryfedorov.teamwork.data.tasks.datasource.remote.model.ApiTasks

class TasksJsonMapper : Mapper<JsonObject, List<ApiTask>> {
    private val gson: Gson = Gson()

    override fun map(srcModel: JsonObject): List<ApiTask> {
        return gson.fromJson(srcModel, ApiTasks::class.java).tasks
    }
}