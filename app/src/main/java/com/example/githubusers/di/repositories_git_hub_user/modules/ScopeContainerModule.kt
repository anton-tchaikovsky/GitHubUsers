package com.example.githubusers.di.repositories_git_hub_user.modules

import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.di.repositories_git_hub_user.IRepositoriesGitHubUserScopeContainer
import com.example.githubusers.di.repositories_git_hub_user.RepositoriesGitHubUserScope
import dagger.Module
import dagger.Provides

@Module
class ScopeContainerModule {

    @RepositoriesGitHubUserScope
    @Provides
    fun repositoriesGitHubUserScopeContainer(gitHubUsersApp: GitHubUsersApp):
            IRepositoriesGitHubUserScopeContainer =
        gitHubUsersApp

}