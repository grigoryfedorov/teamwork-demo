package com.grigoryfedorov.teamwork.ui.projecttasklist

import com.grigoryfedorov.teamwork.ui.Presenter

interface ProjectTaskListPresenter : Presenter {
    interface View {
        fun showTasks()
        fun showError(message: String)
        fun hideError()
        fun showProgress()
        fun hideProgress()

    }

}
