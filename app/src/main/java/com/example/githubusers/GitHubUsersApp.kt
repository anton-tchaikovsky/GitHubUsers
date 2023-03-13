package com.example.githubusers

import android.app.Application
import android.content.Context
import com.example.githubusers.data.repository.GitHubUsersRepositoryImpl
import com.example.githubusers.domain.repository.GitHubUsersRepository

val Context.gitHubUserApp: GitHubUsersApp
    get() = applicationContext as GitHubUsersApp

class GitHubUsersApp : Application() {
    val gitHubUsersRepository: GitHubUsersRepository by lazy {
        GitHubUsersRepositoryImpl()
    }
}