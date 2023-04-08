package com.example.githubusers.di.modules

import com.example.githubusers.data.repository.GitHubImageRepositoryImpl
import com.example.githubusers.data.repository.GitHubUsersRepositoryImpl
import com.example.githubusers.data.repository.RepositoriesGitHubUserRepositoryImpl
import com.example.githubusers.domain.repository.IGitHubImageRepository
import com.example.githubusers.domain.repository.IGitHubUsersRepository
import com.example.githubusers.domain.repository.IRepositoriesGitHubUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun gitHubUsersRepository(): IGitHubUsersRepository =
        GitHubUsersRepositoryImpl()

    @Singleton
    @Provides
    fun repositoriesGitHubUsersRepository(): IRepositoriesGitHubUserRepository =
        RepositoriesGitHubUserRepositoryImpl()

    @Singleton
    @Provides
    fun gitHubImageRepository(): IGitHubImageRepository =
        GitHubImageRepositoryImpl()
}