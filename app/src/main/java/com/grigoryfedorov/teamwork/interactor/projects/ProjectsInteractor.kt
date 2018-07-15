package com.grigoryfedorov.teamwork.interactor.projects

import com.grigoryfedorov.teamwork.domain.Project
import io.reactivex.Observable

interface ProjectsInteractor {
    fun getProjects(): Observable<List<Project>>
}