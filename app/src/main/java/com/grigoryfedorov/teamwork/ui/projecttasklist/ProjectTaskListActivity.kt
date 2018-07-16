package com.grigoryfedorov.teamwork.ui.projecttasklist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.di.ui.projecttasklist.ProjectTaskListActivityModule
import com.grigoryfedorov.teamwork.domain.Task
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class ProjectTaskListActivity : AppCompatActivity(), ProjectTaskListPresenter.View {

    companion object {
        const val EXTRA_PROJECT_ID = "extra_project_id"
    }

    @Inject
    lateinit var presenter: ProjectTaskListPresenter

    private lateinit var projectsListAdapter: ProjectTaskListAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    lateinit var scope: Scope

    override fun onCreate(savedInstanceState: Bundle?) {
        val projectId = intent.getStringExtra(EXTRA_PROJECT_ID)

        val tasks: MutableList<Task> = ArrayList()

        scope = Toothpick.openScopes(application, this)
        scope.installModules(ProjectTaskListActivityModule(this, projectId, tasks))
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, scope)

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

    override fun onDestroy() {
        Toothpick.closeScope(this)
        super.onDestroy()
    }

}