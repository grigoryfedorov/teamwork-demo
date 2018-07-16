package com.grigoryfedorov.teamwork

import android.app.Application
import com.grigoryfedorov.teamwork.di.network.AppModule
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

class TeamWorkApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // com.example.smoothie.MemberInjectorRegistry and com.example.smoothie.FactoryRegistry are classes generated by this project
        // Your project will have these classes in your package (or the one you specify).
        // Please note that the fully qualified name should be used instead of an import.
        // (see https://github.com/stephanenicolas/toothpick/wiki/Factory-and-Member-Injector-registries)
        // If you're not using the reflection free configuration, the next 3 lines can be omitted
        Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
        MemberInjectorRegistryLocator.setRootRegistry(com.grigoryfedorov.teamwork.MemberInjectorRegistry())
        FactoryRegistryLocator.setRootRegistry(com.grigoryfedorov.teamwork.FactoryRegistry())

        val appScope = Toothpick.openScope(this)
        initToothpick(appScope)
    }

    fun initToothpick(appScope: Scope) {
        appScope.installModules(AppModule(this))
    }
}