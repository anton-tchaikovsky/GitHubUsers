package com.example.githubusers.di.modules

import androidx.room.Room
import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.data.repository.cache.GitHubUsersCache
import com.example.githubusers.data.repository.cache.RepositoriesGitHubUserCache
import com.example.githubusers.data.room.DatabaseGitHubUsers
import com.example.githubusers.domain.repository.cache.IGitHubUsersCache
import com.example.githubusers.domain.repository.cache.IRepositoriesGitHubUserCache
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
    fun repositoriesGitHubUserCache(databaseGitHubUsers: DatabaseGitHubUsers): IRepositoriesGitHubUserCache =
        RepositoriesGitHubUserCache(databaseGitHubUsers)

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