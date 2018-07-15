package com.grigoryfedorov.teamwork.network

import com.grigoryfedorov.teamwork.R
import com.grigoryfedorov.teamwork.services.resources.ResourceManager

class TeamWorkApiKeyProvider(private val resourceManager: ResourceManager) : ApiKeyProvider {
    override fun getApiKey(): String = resourceManager.getString(R.string.team_work_projects_api_key)
}