package com.example.githubusers.di.repositories_git_hub_user

import com.example.githubusers.data.repository.RepositoriesGitHubUserRepositoryImpl
import com.example.githubusers.di.repositories_git_hub_user.modules.DatabaseModule
import com.example.githubusers.di.repositories_git_hub_user.modules.RepositoryModule
import com.example.githubusers.di.repositories_git_hub_user.modules.ScopeContainerModule
import com.example.githubusers.ui.repositories_git_hub_user.RepositoriesGitHubUserPresenter
import dagger.Subcomponent

@RepositoriesGitHubUserScope
@Subcomponent (
    modules = [
        RepositoryModule::class,
        ScopeContainerModule::class,
        DatabaseModule::class
    ]
        )
interface RepositoriesGitHubUserSubcomponent {

    fun inject(repositoriesGitHubUserPresenter: RepositoriesGitHubUserPresenter)
    fun inject(repositoriesGitHubUserRepositoryImpl: RepositoriesGitHubUserRepositoryImpl)

}