package com.grigoryfedorov.teamwork.ui.projectslist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.di.ui.projectlist.ProjectsListActivityModule
import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.ui.projecttasklist.ProjectTaskListActivity
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class ProjectsListActivity : AppCompatActivity(), ProjectsListPresenter.View {

    @Inject
    lateinit var presenter: ProjectsListPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    private lateinit var projectsListAdapter: ProjectsListAdapter

    lateinit var scope: Scope

    override fun onCreate(savedInstanceState: Bundle?) {
        val projects: MutableList<Project> = ArrayList()

        scope = Toothpick.openScopes(application, this)
        scope.installModules(ProjectsListActivityModule(this, projects))

        super.onCreate(savedInstanceState)

        Toothpick.inject(this, scope)

        setContentView(R.layout.activity_projects_list)

        progressBar = findViewById(R.id.projects_list_progress_bar)
        errorTextView = findViewById(R.id.projects_list_error_text_view)

        recyclerView = this.findViewById(R.id.projects_list_recycler_view)

        projectsListAdapter = ProjectsListAdapter(this, projects, object : ProjectsListAdapter.Listener {
            override fun onProjectClick(position: Int) {
                presenter.onProjectClick(position)
            }

        })
        recyclerView.adapter = projectsListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
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
        Toothpick.closeScope(this)
        super.onDestroy()
    }

    override fun showProjects() {
        projectsListAdapter.notifyDataSetChanged()
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

    override fun navigateToProjectTasks(projectId: String) {
        val intent = Intent(this, ProjectTaskListActivity::class.java)
        intent.putExtra(ProjectTaskListActivity.EXTRA_PROJECT_ID, projectId)
        startActivity(intent)
    }

}
