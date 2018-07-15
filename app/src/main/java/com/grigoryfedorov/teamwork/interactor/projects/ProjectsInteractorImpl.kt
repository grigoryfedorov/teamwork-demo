package com.grigoryfedorov.teamwork.interactor.projects

import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.repository.ProjectsRepository
import io.reactivex.Observable

class ProjectsInteractorImpl(
        private val projectsRepository: ProjectsRepository
) : ProjectsInteractor {

    override fun getProjects(): Observable<List<Project>> = projectsRepository.getProjects()

}