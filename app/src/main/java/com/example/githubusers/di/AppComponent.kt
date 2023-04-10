package com.example.githubusers.di

import com.example.githubusers.data.repository.GitHubImageRepositoryImpl
import com.example.githubusers.data.repository.GitHubUsersRepositoryImpl
import com.example.githubusers.data.repository.RepositoriesGitHubUserRepositoryImpl
import com.example.githubusers.di.modules.*
import com.example.githubusers.ui.git_hub_image.GitHubImagePresenter
import com.example.githubusers.ui.git_hub_users.GitHubUsersActivity
import com.example.githubusers.ui.git_hub_users.GitHubUsersPresenter
import com.example.githubusers.ui.repositories_git_hub_user.RepositoriesGitHubUserPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        AppModule::class,
        ApiModule::class,
        RepositoryModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {
    fun inject(gitHubUsersActivity: GitHubUsersActivity)

    fun inject(gitHubUsersPresenter: GitHubUsersPresenter)
    fun inject(repositoriesGitHubUserPresenter: RepositoriesGitHubUserPresenter)
    fun inject(gitHubImagePresenter: GitHubImagePresenter)

    fun inject(gitHubUsersRepositoryImpl: GitHubUsersRepositoryImpl)
    fun inject(repositoriesGitHubUserRepositoryImpl: RepositoriesGitHubUserRepositoryImpl)
    fun inject(gitHubImageRepositoryImpl: GitHubImageRepositoryImpl)
}