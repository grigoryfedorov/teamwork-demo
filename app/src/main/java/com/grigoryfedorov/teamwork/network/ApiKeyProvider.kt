package com.grigoryfedorov.teamwork.network

interface ApiKeyProvider {
    fun getApiKey(): String
}