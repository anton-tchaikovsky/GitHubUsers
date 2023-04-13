package com.example.githubusers.di.git_hub_app

import com.example.githubusers.data.repository.GitHubUsersRepositoryImpl
import com.example.githubusers.data.repository.git_hub_image.GitHubImageLoaderRepositoryImpl
import com.example.githubusers.di.git_hub_app.modules.*
import com.example.githubusers.di.git_hub_image.GitHubImageSubcomponent
import com.example.githubusers.di.repositories_git_hub_user.RepositoriesGitHubUserSubcomponent
import com.example.githubusers.ui.avatar_git_hub_user.AvatarGitHubUserFragment
import com.example.githubusers.ui.git_hub_image.GitHubImageFragment
import com.example.githubusers.ui.git_hub_users.GitHubUsersActivity
import com.example.githubusers.ui.git_hub_users.GitHubUsersPresenter
import com.example.githubusers.ui.git_hub_users.git_nub_users_recycle_view.ItemGitHubUsersViewHolder
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        AppModule::class,
        ApiModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        ImageLoaderModule::class,
        StorageDirectoryModule::class
    ]
)
interface AppComponent {
    fun gitHubImageSubcomponent(): GitHubImageSubcomponent
    fun repositoriesGitHubUserSubcomponent(): RepositoriesGitHubUserSubcomponent

    fun inject(gitHubUsersActivity: GitHubUsersActivity)

    fun inject(gitHubUsersPresenter: GitHubUsersPresenter)

    fun inject(gitHubUsersRepositoryImpl: GitHubUsersRepositoryImpl)
    fun inject(gitHubImageLoaderRepositoryImpl:GitHubImageLoaderRepositoryImpl)

    fun inject(avatarGitHubUserFragment: AvatarGitHubUserFragment)
    fun inject(gitHubImageFragment: GitHubImageFragment)
    fun inject (itemGitHubUsersViewHolder: ItemGitHubUsersViewHolder)
}