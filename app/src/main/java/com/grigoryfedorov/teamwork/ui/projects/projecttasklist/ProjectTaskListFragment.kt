package com.grigoryfedorov.teamwork.ui.projects.projecttasklist

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
import com.grigoryfedorov.teamwork.di.ui.projects.projecttasklist.ProjectTaskListActivityModule
import com.grigoryfedorov.teamwork.domain.Task
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class ProjectTaskListFragment : Fragment(), ProjectTaskListPresenter.View {
    @Inject
    lateinit var presenter: ProjectTaskListPresenter

    private lateinit var projectsListAdapter: ProjectTaskListAdapter

    private lateinit var title: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    lateinit var scope: Scope

    override fun onCreate(savedInstanceState: Bundle?) {
        scope = Toothpick.openScopes(AppScope::class.java, ProjectsScope::class.java, ProjectTaskListFragment::class.java)
        scope.installModules(ProjectTaskListActivityModule(this))
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, scope)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.project_task_list, container, false)

        progressBar = rootView.findViewById(R.id.projects_task_list_progress_bar)
        errorTextView = rootView.findViewById(R.id.projects_task_list_error_text_view)

        recyclerView = rootView.findViewById(R.id.projects_task_list_recycler_view)

        projectsListAdapter = ProjectTaskListAdapter()
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

    override fun showTasks(newTasks: List<Task>) {
        projectsListAdapter.updateTasks(newTasks)
    }

    override fun onDestroy() {
        Toothpick.closeScope(ProjectTaskListFragment::class.java)
        super.onDestroy()
    }

}