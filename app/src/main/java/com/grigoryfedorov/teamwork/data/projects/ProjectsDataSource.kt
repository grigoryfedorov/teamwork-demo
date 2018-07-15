package com.grigoryfedorov.teamwork.data.projects

import com.grigoryfedorov.teamwork.domain.Project
import io.reactivex.Observable

interface ProjectsDataSource {
    fun getProjects(): Observable<List<Project>>
}