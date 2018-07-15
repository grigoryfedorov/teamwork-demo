package com.grigoryfedorov.teamwork.data.projects

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET

interface ProjectsApiService {

    @GET("projects.json")
    fun getProjects(): Observable<JsonObject>

}
