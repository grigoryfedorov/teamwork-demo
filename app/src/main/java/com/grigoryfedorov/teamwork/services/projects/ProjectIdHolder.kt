package com.grigoryfedorov.teamwork.services.projects

import com.grigoryfedorov.teamwork.di.ui.projects.ProjectsScope
import com.grigoryfedorov.teamwork.domain.Project
import javax.inject.Inject

@ProjectsScope
class ProjectIdHolder @Inject constructor() {
    var selectedProject: Project = Project.EMPTY
}