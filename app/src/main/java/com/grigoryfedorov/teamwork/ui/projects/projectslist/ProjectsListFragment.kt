package com.grigoryfedorov.teamwork.ui.projects.projectslist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.di.AppScope
import com.grigoryfedorov.teamwork.di.ui.projects.ProjectsScope
import com.grigoryfedorov.teamwork.di.ui.projects.projectlist.ProjectsListModule
import com.grigoryfedorov.teamwork.domain.Project
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class ProjectsListFragment : Fragment(), ProjectsListPresenter.View {
    @Inject
    lateinit var presenter: ProjectsListPresenter

    private lateinit var recyclerView: RecyclerView

    private lateinit var title: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView
    private lateinit var projectsListAdapter: ProjectsListAdapter

    lateinit var scope: Scope

    override fun onCreate(savedInstanceState: Bundle?) {
        scope = Toothpick.openScopes(AppScope::class.java, ProjectsScope::class.java, ProjectsListFragment::class.java)
        scope.installModules(ProjectsListModule(this))

        super.onCreate(savedInstanceState)

        Toothpick.inject(this, scope)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.projects_list, container, false)

        progressBar = rootView.findViewById(R.id.projects_list_progress_bar)
        errorTextView = rootView.findViewById(R.id.projects_list_error_text_view)

        recyclerView = rootView.findViewById(R.id.projects_list_recycler_view)

        projectsListAdapter = ProjectsListAdapter(context!!, object : ProjectsListAdapter.Listener {
            override fun onProjectClick(position: Int) {
                presenter.onProjectClick(position)
            }

        })
        recyclerView.adapter = projectsListAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        title = rootView.findViewById(R.id.toolbar_title)

        return rootView
    }

    override fun showTitle(title: String) {
        this.title.text = title
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        Toothpick.closeScope(ProjectsListFragment::class.java)
        super.onDestroy()
    }

    override fun showProjects(newProjects: List<Project>) {
        projectsListAdapter.updateProjects(newProjects)
    }

    override fun showError(message: String) {
        errorTextView.text = message
        errorTextView.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorTextView.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

}
