package com.example.githubusers

import android.app.Application
import com.example.githubusers.di.AppComponent
import com.example.githubusers.di.DaggerAppComponent
import com.example.githubusers.di.modules.AppModule
import com.example.githubusers.di.modules.git_hub_image.GitHubImageSubcomponent
import com.example.githubusers.di.modules.git_hub_image.IGitHubImageScopeContainer

class GitHubUsersApp : Application(), IGitHubImageScopeContainer {

    lateinit var appComponent: AppComponent

    var gitHubImageSubcomponent: GitHubImageSubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: GitHubUsersApp
    }

    fun initGitHubImageSubcomponent() =
       appComponent.gitHubImageSubcomponent().also {
           gitHubImageSubcomponent = it
       }

    override fun releaseGitHubImageScope() {
        gitHubImageSubcomponent = null
    }
}
