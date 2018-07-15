package com.grigoryfedorov.teamwork.data.tasks.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ApiTasks(
        @SerializedName("todo-items")
        val tasks: List<ApiTask>
)