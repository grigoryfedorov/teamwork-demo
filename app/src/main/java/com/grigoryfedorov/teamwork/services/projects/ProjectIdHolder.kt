package com.grigoryfedorov.teamwork.services.projects

import com.grigoryfedorov.teamwork.di.ui.projects.ProjectsScope
import javax.inject.Inject

@ProjectsScope
class ProjectIdHolder @Inject constructor() {
    var selectedProjectId: String = ""

}