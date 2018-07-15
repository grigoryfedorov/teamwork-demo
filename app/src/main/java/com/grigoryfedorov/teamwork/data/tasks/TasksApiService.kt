package com.grigoryfedorov.teamwork.data.tasks

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface TasksApiService {
    @GET("/projects/{projectId}/tasks.json")
    fun getTasksForProject(@Path("projectId") projectId: String): Observable<JsonObject>
}