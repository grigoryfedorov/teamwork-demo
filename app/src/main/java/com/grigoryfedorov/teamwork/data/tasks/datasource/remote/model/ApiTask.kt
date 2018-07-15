package com.grigoryfedorov.teamwork.data.tasks.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ApiTask(
        @SerializedName("id")
        val id: String,

        @SerializedName("content")
        val content: String
)