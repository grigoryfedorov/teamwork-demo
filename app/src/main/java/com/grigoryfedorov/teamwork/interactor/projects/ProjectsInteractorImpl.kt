package com.grigoryfedorov.teamwork.interactor.projects

import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsInteractorImpl @Inject constructor(
        private val projectsRepository: ProjectsRepository
) : ProjectsInteractor {

    override fun getProjects(): Observable<List<Project>> = projectsRepository.getProjects()

}