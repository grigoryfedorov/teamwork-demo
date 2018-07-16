package com.grigoryfedorov.teamwork.network

import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.services.resources.ResourceManager
import javax.inject.Inject

class TeamWorkProjectsApiHostProvider @Inject constructor(
        private val resourceManager: ResourceManager
) : HostProvider {
    override fun getHost(): String = resourceManager.getString(R.string.team_work_projects_api_host)
}