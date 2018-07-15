package com.grigoryfedorov.teamwork.data.projects.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ApiProject(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String
)