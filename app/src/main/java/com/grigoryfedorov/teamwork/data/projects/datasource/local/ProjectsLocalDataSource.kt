package com.grigoryfedorov.teamwork.data.projects.datasource.local

import com.grigoryfedorov.teamwork.data.projects.ProjectsDataSource
import com.grigoryfedorov.teamwork.domain.Project
import io.reactivex.Observable

class ProjectsLocalDataSource : ProjectsDataSource {

    private val projects: MutableList<Project> = ArrayList()
    private val lock = Any()

    override fun getProjects(): Observable<List<Project>> {
        synchronized(lock) {
            return Observable.just(ArrayList(projects))
        }
    }

    fun putProjects(newProjects: List<Project>) {
        synchronized(lock) {
            projects.clear()
            projects.addAll(newProjects)
        }
    }

}