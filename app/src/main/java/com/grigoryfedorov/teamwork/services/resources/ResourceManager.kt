package com.grigoryfedorov.teamwork.services.resources

import android.support.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes stringId: Int): String
}