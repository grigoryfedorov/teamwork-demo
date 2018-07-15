package com.grigoryfedorov.teamwork.ui.projectslist

import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.domain.Project
import com.grigoryfedorov.teamwork.interactor.projects.ProjectsInteractor
import com.grigoryfedorov.teamwork.services.resources.ResourceManager
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProjectsListPresenterImplTest {

    @Mock
    private lateinit var view: ProjectsListPresenter.View

    @Mock
    private lateinit var projectsInteractor: ProjectsInteractor

    @Mock
    private lateinit var resourceManager: ResourceManager

    private lateinit var projects: MutableList<Project>

    private lateinit var presenter: ProjectsListPresenterImpl

    @Before
    fun setUp() {
        projects = ArrayList()

        presenter = ProjectsListPresenterImpl(
                view,
                projectsInteractor,
                projects,
                resourceManager,
                Schedulers.trampoline(),
                Schedulers.trampoline()
        )
    }

    @Test
    fun shouldUpdateAndShowProjectsOnStartGetProjectsSucceeded() {
        val testProjects = listOf(Project("1", "one"), Project("2", "two"))

        `when`(projectsInteractor.getProjects()).thenReturn(Observable.just(testProjects))

        presenter.onStart()

        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).showProjects()

        assertEquals(testProjects, projects)
        verify(view, never()).showError(anyString())
    }

    @Test
    fun shouldShowErrorOnStartIfGetProjectsFailed() {
        `when`(projectsInteractor.getProjects()).thenReturn(Observable.error(Throwable("Error")))
        `when`(resourceManager.getString(R.string.general_error_message)).thenReturn("General error message")

        presenter.onStart()

        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).showError("General error message")

        assertTrue(projects.isEmpty())
        verify(view, never()).showProjects()
    }


}