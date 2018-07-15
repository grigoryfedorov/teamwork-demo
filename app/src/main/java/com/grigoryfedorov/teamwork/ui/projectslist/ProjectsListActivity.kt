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
import com.grigoryfedorov.teamwork.data.projects.ProjectsRepositoryImpl
import com.grigoryfedorov.teamwork.data.projects.datasource.local.ProjectsLocalDataSource
import com.grigoryfedorov.teamwork.data.projects.datasource.remote.ProjectsRemoteDataSource
import com.grigoryfedorov.teamwork.data.projects.datasource.remote.mapper.ProjectsEntityMapper
import com.grigoryfedorov.teamwork.data.projects.datasource.remote.mapper.ProjectsJsonMapper
import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.interactor.projects.ProjectsInteractorImpl
import com.grigoryfedorov.teamwork.network.TeamWorkApiKeyProvider
import com.grigoryfedorov.teamwork.network.TeamWorkProjectsApi
import com.grigoryfedorov.teamwork.network.TeamWorkProjectsApiHostProvider
import com.grigoryfedorov.teamwork.services.resources.ResourceManagerImpl
import com.grigoryfedorov.teamwork.ui.projecttasklist.ProjectTaskListActivity

class ProjectsListActivity : AppCompatActivity(), ProjectsListPresenter.View {

    private lateinit var presenter: ProjectsListPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    private lateinit var projectsListAdapter: ProjectsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val projects: MutableList<Project> = ArrayList()

        val resourceManager = ResourceManagerImpl(this)
        val teamWorkProjectsApiHostProvider = TeamWorkProjectsApiHostProvider(resourceManager)
        val teamWorkApiKeyProvider = TeamWorkApiKeyProvider(resourceManager)
        val teamWorkProjectsApi = TeamWorkProjectsApi(teamWorkProjectsApiHostProvider, teamWorkApiKeyProvider)
        val projectsJsonMapper = ProjectsJsonMapper()
        val projectsEntityMapper = ProjectsEntityMapper()
        val projectsRemoteDataSource = ProjectsRemoteDataSource(teamWorkProjectsApi, projectsJsonMapper, projectsEntityMapper)
        val projectsLocalDataSource = ProjectsLocalDataSource()
        val projectsRepository = ProjectsRepositoryImpl(projectsLocalDataSource, projectsRemoteDataSource)
        val projectsInteractor = ProjectsInteractorImpl(projectsRepository)
        presenter = ProjectsListPresenterImpl(this, projectsInteractor, projects, resourceManager)

        setContentView(R.layout.activity_projects_list)

        progressBar = findViewById(R.id.projects_list_progress_bar)
        errorTextView = findViewById(R.id.projects_list_error_text_view)

        recyclerView = findViewById(R.id.projects_list_recycler_view)

        projectsListAdapter = ProjectsListAdapter(projects, object : ProjectsListAdapter.Listener {
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
