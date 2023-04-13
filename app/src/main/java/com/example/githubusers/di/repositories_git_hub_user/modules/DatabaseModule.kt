package com.example.githubusers.di.repositories_git_hub_user.modules

import com.example.githubusers.data.repository.cache.RepositoriesGitHubUserCache
import com.example.githubusers.data.room.DatabaseGitHubUsers
import com.example.githubusers.di.repositories_git_hub_user.RepositoriesGitHubUserScope
import com.example.githubusers.domain.repository.cache.IRepositoriesGitHubUserCache
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @RepositoriesGitHubUserScope
    @Provides
    fun repositoriesGitHubUserCache(databaseGitHubUsers: DatabaseGitHubUsers): IRepositoriesGitHubUserCache =
        RepositoriesGitHubUserCache(databaseGitHubUsers)

}
