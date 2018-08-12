package com.grigoryfedorov.teamwork.ui.projects

import com.grigoryfedorov.teamwork.di.ui.projects.ProjectsScope
import com.grigoryfedorov.teamwork.routing.Router
import com.grigoryfedorov.teamwork.routing.Screen
import com.grigoryfedorov.teamwork.ui.BasePresenter
import javax.inject.Inject

@ProjectsScope
class ProjectsPresenterImpl @Inject constructor(
        private val router: Router
) : ProjectsPresenter, BasePresenter() {

    private var isFirstStart = true

    override fun onStart() {
        super.onStart()

        if (isFirstStart) {
            router.navigateTo(Screen.PROJECT_LIST)
            isFirstStart = false
        }
    }
}