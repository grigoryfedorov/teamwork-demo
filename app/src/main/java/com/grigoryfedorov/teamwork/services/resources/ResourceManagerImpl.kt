package com.grigoryfedorov.teamwork.services.resources

import android.content.Context
import android.support.annotation.StringRes

class ResourceManagerImpl(private val context: Context) : ResourceManager {
    override fun getString(@StringRes stringId: Int): String = context.getString(stringId)
}