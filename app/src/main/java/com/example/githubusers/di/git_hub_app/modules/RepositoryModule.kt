package com.example.githubusers.di.git_hub_app.modules

import com.example.githubusers.data.repository.GitHubUsersRepositoryImpl
import com.example.githubusers.data.repository.git_hub_image.GitHubImageLoaderRepositoryImpl
import com.example.githubusers.domain.repository.IGitHubUsersRepository
import com.example.githubusers.domain.repository.git_hub_image.IGitHubImageLoaderRepository
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
    fun gitHubImageLoaderRepository(): IGitHubImageLoaderRepository =
        GitHubImageLoaderRepositoryImpl()

}