package com.grigoryfedorov.teamwork.domain

data class Project(
        val id: String,
        val name: String,
        val logo: String,
        val status: String,
        val description: String
) {
    companion object {
        val EMPTY = Project("", "", "", "", "")
    }
}