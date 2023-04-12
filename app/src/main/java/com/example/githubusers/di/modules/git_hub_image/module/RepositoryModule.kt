package com.example.githubusers.di.modules.git_hub_image.module

import com.example.githubusers.data.repository.git_hub_image.GitHubImageRepositoryImpl
import com.example.githubusers.di.modules.git_hub_image.GitHubImageScope
import com.example.githubusers.domain.repository.git_hub_image.IGitHubImageRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @GitHubImageScope
    @Provides
    fun gitHubImageRepository(): IGitHubImageRepository =
        GitHubImageRepositoryImpl()
}