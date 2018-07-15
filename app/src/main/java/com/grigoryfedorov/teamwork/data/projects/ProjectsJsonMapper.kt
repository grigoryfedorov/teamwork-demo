package com.grigoryfedorov.teamwork.data.projects

import com.google.gson.Gson
import com.google.gson.JsonObject

class ProjectsJsonMapper {
    private val gson: Gson = Gson()

    fun map(jsonObject: JsonObject): List<ApiProject> {
        return gson.fromJson(jsonObject, ApiProjects::class.java).projects
    }
}
