package com.example.githubusers.di.modules.git_hub_image.module

import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.di.modules.git_hub_image.GitHubImageScope
import com.example.githubusers.di.modules.git_hub_image.IGitHubImageScopeContainer
import dagger.Module
import dagger.Provides

@Module
class ScopeContainerModule {

    @GitHubImageScope
    @Provides
    fun gitHubImageScopeContainer(gitHubUsersApp: GitHubUsersApp): IGitHubImageScopeContainer =
        gitHubUsersApp

}