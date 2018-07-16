package com.grigoryfedorov.teamwork.data.projects.datasource.remote.mapper

import com.grigoryfedorov.teamwork.data.Mapper
import com.grigoryfedorov.teamwork.data.projects.datasource.remote.model.ApiProject
import com.grigoryfedorov.teamwork.domain.Project
import javax.inject.Inject

class ProjectsEntityMapper @Inject constructor() : Mapper<List<ApiProject>, List<Project>> {

    override fun map(srcModel: List<ApiProject>): List<Project> {
        val projects = ArrayList<Project>(srcModel.size)

        for (apiProject in srcModel) {
            projects.add(Project(
                    id = apiProject.id,
                    name = apiProject.name)
            )
        }

        return projects
    }
}
