package com.grigoryfedorov.teamwork.ui.projects

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.di.AppScope
import com.grigoryfedorov.teamwork.di.ui.projects.ProjectsModule
import com.grigoryfedorov.teamwork.di.ui.projects.ProjectsScope
import com.grigoryfedorov.teamwork.routing.Navigator
import com.grigoryfedorov.teamwork.routing.Router
import com.grigoryfedorov.teamwork.routing.Screen
import com.grigoryfedorov.teamwork.ui.projects.projectslist.ProjectsListFragment
import com.grigoryfedorov.teamwork.ui.projects.projecttasklist.ProjectTaskListFragment
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class ProjectsActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: ProjectsPresenter
    @Inject
    lateinit var router: Router
    lateinit var scope: Scope

    override fun onCreate(savedInstanceState: Bundle?) {
        scope = Toothpick.openScopes(AppScope::class.java, ProjectsScope::class.java)
        scope.installModules(ProjectsModule())
        Toothpick.inject(this, scope)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)
    }

    override fun onStart() {
        super.onStart()
        router.attachNavigator(ProjectsNavigator())
        presenter.onStart()
    }

    override fun onStop() {
        presenter.onStop()
        router.removeNavigator()
        super.onStop()
    }

    override fun onDestroy() {
        if (isFinishing) {
            Toothpick.closeScope(ProjectsScope::class.java)
        }

        super.onDestroy()
    }

    inner class ProjectsNavigator : Navigator {
        override fun navigateTo(screen: Screen) {
            var fragment = supportFragmentManager.findFragmentByTag(screen.tag)
            if (fragment == null) {
                fragment = createFragment(screen)
            }

            if (fragment != null) {
                val transaction = supportFragmentManager.beginTransaction()
                        .replace(R.id.projects_container, fragment)
                if (needAddToBackStack(screen)) {
                    transaction.addToBackStack(screen.tag)
                }
                transaction.commit()
            }

        }

        private fun createFragment(screen: Screen): Fragment? {
            return when (screen) {
                Screen.PROJECT_LIST -> ProjectsListFragment()
                Screen.PROJECT_TASK_LIST -> ProjectTaskListFragment()
            }
        }

        private fun needAddToBackStack(screen: Screen): Boolean {
            return when (screen) {
                Screen.PROJECT_LIST -> false
                Screen.PROJECT_TASK_LIST -> true
            }
        }

    }
}
