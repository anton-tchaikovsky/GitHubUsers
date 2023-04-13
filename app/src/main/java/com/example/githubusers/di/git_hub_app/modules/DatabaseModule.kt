package com.example.githubusers.di.git_hub_app.modules

import androidx.room.Room
import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.data.repository.cache.GitHubUsersCache
import com.example.githubusers.data.room.DatabaseGitHubUsers
import com.example.githubusers.domain.repository.cache.IGitHubUsersCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun gitHubUsersCache(databaseGitHubUsers: DatabaseGitHubUsers): IGitHubUsersCache =
        GitHubUsersCache(databaseGitHubUsers)

    @Singleton
    @Provides
    fun databaseGitHubUsers(gitHubUsersApp:GitHubUsersApp): DatabaseGitHubUsers =
        Room.databaseBuilder(
            gitHubUsersApp,
            DatabaseGitHubUsers::class.java,
            DATABASE_NAME
        ).build()

    companion object {
        private const val DATABASE_NAME = "GitHubUser.db"
    }

}