package com.example.githubusers

import android.app.Application
import android.content.Context
import com.example.githubusers.data.repository.GitHubUsersRepositoryImpl
import com.example.githubusers.domain.repository.GitHubUsersRepository
import com.github.terrakok.cicerone.Cicerone

val Context.gitHubUserApp: GitHubUsersApp
    get() = applicationContext as GitHubUsersApp

class GitHubUsersApp : Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val gitHubUsersRepository: GitHubUsersRepository by lazy {
        GitHubUsersRepositoryImpl()
    }
//    companion object {
//        internal lateinit var INSTANCE: GitHubUsersApp
//            private set
//    }
//    override fun onCreate() {
//        super.onCreate()
//        INSTANCE = this
//    }
//
}