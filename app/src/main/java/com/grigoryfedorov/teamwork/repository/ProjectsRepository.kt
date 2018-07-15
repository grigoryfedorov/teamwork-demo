package com.grigoryfedorov.teamwork.repository

import com.grigoryfedorov.teamwork.domain.Project
import io.reactivex.Observable

interface ProjectsRepository {
    fun getProjects(): Observable<List<Project>>
}