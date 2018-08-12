package com.grigoryfedorov.teamwork.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.grigoryfedorov.teamwork.data.projects.ProjectsApiService
import com.grigoryfedorov.teamwork.data.tasks.TasksApiService
import com.grigoryfedorov.teamwork.services.encoders.Base64Encoder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class TeamWorkProjectsApi @Inject constructor(hostProvider: HostProvider, apiKeyProvider: ApiKeyProvider) {

    private val gson: Gson = GsonBuilder().create()

    private val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(BasicAuthorizationInterceptor(apiKeyProvider, Base64Encoder()))
            .addInterceptor(getLoggingInterceptor())
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(hostProvider.getHost())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val projectsApiService: ProjectsApiService = retrofit.create(ProjectsApiService::class.java)

    private val tasksApiService: TasksApiService = retrofit.create(TasksApiService::class.java)


    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    fun getProjectsApiService(): ProjectsApiService = projectsApiService

    fun getTasksApiService(): TasksApiService = tasksApiService

}
