package com.grigoryfedorov.teamwork.network

import com.google.gson.annotations.SerializedName

open class ApiResponse(
        @SerializedName("STATUS")
        val status: String
)
