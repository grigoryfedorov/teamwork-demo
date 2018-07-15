package com.grigoryfedorov.teamwork.data.projects.datasource.remote.mapper

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.grigoryfedorov.teamwork.data.Mapper
import com.grigoryfedorov.teamwork.data.projects.datasource.remote.model.ApiProject
import com.grigoryfedorov.teamwork.data.projects.datasource.remote.model.ApiProjects

class ProjectsJsonMapper : Mapper<JsonObject, List<ApiProject>> {
    private val gson: Gson = Gson()

    override fun map(srcModel: JsonObject): List<ApiProject> {
        return gson.fromJson(srcModel, ApiProjects::class.java).projects
    }
}
