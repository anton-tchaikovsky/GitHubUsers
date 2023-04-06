package com.example.githubusers

import android.app.Application
import android.content.Context
import com.example.githubusers.data.repository.network.NetWorkStatus
import com.example.githubusers.data.repository.GitHubRepositoryImpl
import com.example.githubusers.data.room.DatabaseGitHubUsers
import com.example.githubusers.domain.repository.IGitHubRepository
import com.github.terrakok.cicerone.Cicerone

val Context.gitHubUserApp: GitHubUsersApp
    get() = applicationContext as GitHubUsersApp

class GitHubUsersApp : Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val gitHubRepository: IGitHubRepository by lazy {
        GitHubRepositoryImpl(NetWorkStatus(this))
    }

    override fun onCreate() {
        super.onCreate()
        DatabaseGitHubUsers.createInstanceDatabase(this)
    }

}