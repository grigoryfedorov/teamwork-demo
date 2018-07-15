package com.grigoryfedorov.teamwork.network

import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.services.resources.ResourceManager

class TeamWorkProjectsApiHostProvider(private val resourceManager: ResourceManager) : HostProvider {
    override fun getHost(): String = resourceManager.getString(R.string.team_work_projects_api_host)
}