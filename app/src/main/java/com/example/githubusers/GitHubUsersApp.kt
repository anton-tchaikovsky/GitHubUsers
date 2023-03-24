package com.example.githubusers

import android.app.Application
import android.content.Context
import com.example.githubusers.data.repository.GitHubRepositoryImpl
import com.example.githubusers.domain.repository.GitHubRepository
import com.github.terrakok.cicerone.Cicerone

val Context.gitHubUserApp: GitHubUsersApp
    get() = applicationContext as GitHubUsersApp

class GitHubUsersApp : Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val gitHubRepository: GitHubRepository by lazy {
        GitHubRepositoryImpl()
    }

}