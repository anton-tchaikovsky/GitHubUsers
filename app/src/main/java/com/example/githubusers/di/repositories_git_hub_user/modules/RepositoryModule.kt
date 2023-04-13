package com.example.githubusers.di.repositories_git_hub_user.modules

import com.example.githubusers.data.repository.RepositoriesGitHubUserRepositoryImpl
import com.example.githubusers.di.repositories_git_hub_user.RepositoriesGitHubUserScope
import com.example.githubusers.domain.repository.IRepositoriesGitHubUserRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @RepositoriesGitHubUserScope
    @Provides
    fun repositoriesGitHubUserRepository(): IRepositoriesGitHubUserRepository =
        RepositoriesGitHubUserRepositoryImpl()

}