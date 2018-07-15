package com.grigoryfedorov.teamwork.data.projects

import com.grigoryfedorov.teamwork.domain.Project

class ProjectsEntityMapper {

    fun map(apiProjects: List<ApiProject>): List<Project> {
        val projects = ArrayList<Project>(apiProjects.size)

        for (apiProject in apiProjects) {
            projects.add(Project(
                    id = apiProject.id,
                    name = apiProject.name)
            )
        }

        return projects
    }
}
