package com.grigoryfedorov.teamwork.ui.projecttasklist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.data.tasks.TasksRepositoryImpl
import com.grigoryfedorov.teamwork.data.tasks.datasource.local.TasksLocalDataSource
import com.grigoryfedorov.teamwork.data.tasks.datasource.remote.TasksRemoteDataSource
import com.grigoryfedorov.teamwork.data.tasks.datasource.remote.mapper.TasksEntityMapper
import com.grigoryfedorov.teamwork.data.tasks.datasource.remote.mapper.TasksJsonMapper
import com.grigoryfedorov.teamwork.domain.Task
import com.grigoryfedorov.teamwork.interactor.tasks.TasksInteractorImpl
import com.grigoryfedorov.teamwork.network.TeamWorkApiKeyProvider
import com.grigoryfedorov.teamwork.network.TeamWorkProjectsApi
import com.grigoryfedorov.teamwork.network.TeamWorkProjectsApiHostProvider
import com.grigoryfedorov.teamwork.services.resources.ResourceManagerImpl

class ProjectTaskListActivity : AppCompatActivity(), ProjectTaskListPresenter.View {

    companion object {
        const val EXTRA_PROJECT_ID = "extra_project_id"
    }

    private lateinit var presenter: ProjectTaskListPresenter

    private lateinit var projectsListAdapter: ProjectTaskListAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val projectId = intent.getStringExtra(EXTRA_PROJECT_ID)

        val tasks: MutableList<Task> = ArrayList()

        val resourceManager = ResourceManagerImpl(this)
        val teamWorkProjectsApiHostProvider = TeamWorkProjectsApiHostProvider(resourceManager)
        val teamWorkApiKeyProvider = TeamWorkApiKeyProvider(resourceManager)
        val teamWorkProjectsApi = TeamWorkProjectsApi(teamWorkProjectsApiHostProvider, teamWorkApiKeyProvider)
        val tasksJsonMapper = TasksJsonMapper()
        val tasksEntityMapper = TasksEntityMapper()

        val tasksLocalDataSource = TasksLocalDataSource()
        val remoteTasksDataSource = TasksRemoteDataSource(teamWorkProjectsApi, tasksJsonMapper, tasksEntityMapper)
        val tasksRepository = TasksRepositoryImpl(tasksLocalDataSource, remoteTasksDataSource)
        val tasksInteractor = TasksInteractorImpl(tasksRepository)

        presenter = ProjectTaskListPresenterImpl(this, tasksInteractor, projectId, tasks, resourceManager)

        setContentView(R.layout.activity_project_task_list)

        progressBar = findViewById(R.id.projects_task_list_progress_bar)
        errorTextView = findViewById(R.id.projects_task_list_error_text_view)

        recyclerView = findViewById(R.id.projects_task_list_recycler_view)

        projectsListAdapter = ProjectTaskListAdapter(tasks)
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

    override fun showTasks() {
        projectsListAdapter.notifyDataSetChanged()
    }


}