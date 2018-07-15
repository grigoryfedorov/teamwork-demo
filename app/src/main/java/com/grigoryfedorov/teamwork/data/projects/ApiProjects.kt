package com.grigoryfedorov.teamwork.data.projects

import com.google.gson.annotations.SerializedName

data class ApiProjects(
        @SerializedName("projects")
        val projects: List<ApiProject>,
        @SerializedName("status")
        val status: String
)