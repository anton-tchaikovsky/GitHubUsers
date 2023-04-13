package com.example.githubusers

import android.app.Application
import com.example.githubusers.di.git_hub_app.AppComponent
import com.example.githubusers.di.git_hub_app.DaggerAppComponent
import com.example.githubusers.di.git_hub_app.modules.AppModule
import com.example.githubusers.di.git_hub_image.GitHubImageSubcomponent
import com.example.githubusers.di.git_hub_image.IGitHubImageScopeContainer
import com.example.githubusers.di.repositories_git_hub_user.IRepositoriesGitHubUserScopeContainer
import com.example.githubusers.di.repositories_git_hub_user.RepositoriesGitHubUserSubcomponent

class GitHubUsersApp : Application(), IGitHubImageScopeContainer,
    IRepositoriesGitHubUserScopeContainer {

    lateinit var appComponent: AppComponent

    var gitHubImageSubcomponent: GitHubImageSubcomponent? = null
        private set

    var repositoriesGitHubUserSubcomponent: RepositoriesGitHubUserSubcomponent? = null
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

    fun initRepositoriesGitHubUserSubcomponent() =
       appComponent.repositoriesGitHubUserSubcomponent().also {
          repositoriesGitHubUserSubcomponent = it
       }

    fun initGitHubImageSubcomponent() =
        appComponent.gitHubImageSubcomponent().also {
            gitHubImageSubcomponent = it
        }

    override fun releaseGitHubImageScope() {
        gitHubImageSubcomponent = null
    }

    override fun releaseRepositoriesGitHubUserScope() {
        repositoriesGitHubUserSubcomponent = null
    }
}
