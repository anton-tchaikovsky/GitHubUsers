package com.example.githubusers.di

import com.example.githubusers.di.modules.AppModule
import com.example.githubusers.di.modules.CiceroneModule
import com.example.githubusers.ui.git_hub_users.GitHubUsersActivity
import com.example.githubusers.ui.git_hub_users.GitHubUsersPresenter
import com.example.githubusers.ui.repositories_git_hub_user.RepositoriesGitHubUserFragment
import com.example.githubusers.ui.repositories_git_hub_user.RepositoriesGitHubUserPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(gitHubUsersActivity: GitHubUsersActivity)
    fun inject(repositoriesGitHubUserFragment: RepositoriesGitHubUserFragment)
    fun inject (gitHubUsersPresenter: GitHubUsersPresenter)
    fun inject (repositoriesGitHubUserPresenter: RepositoriesGitHubUserPresenter)
}