package com.grigoryfedorov.teamwork.routing

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Router @Inject constructor() {

    private var navigator: Navigator? = null

    fun attachNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    fun removeNavigator() {
        this.navigator = null
    }

    fun navigateTo(screen: Screen) {
        navigator?.navigateTo(screen)
    }

    fun back() {
        navigator?.back()
    }
}
