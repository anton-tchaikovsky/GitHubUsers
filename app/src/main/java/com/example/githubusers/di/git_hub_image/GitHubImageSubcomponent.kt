package com.example.githubusers.di.git_hub_image

import com.example.githubusers.data.repository.git_hub_image.GitHubImageRepositoryImpl
import com.example.githubusers.di.git_hub_image.modules.RepositoryModule
import com.example.githubusers.di.git_hub_image.modules.ScopeContainerModule
import com.example.githubusers.di.git_hub_image.modules.StorageDirectoryModule
import com.example.githubusers.ui.git_hub_image.GitHubImagePresenter
import dagger.Subcomponent

@GitHubImageScope
@Subcomponent (
    modules = [
        RepositoryModule::class,
        StorageDirectoryModule::class,
        ScopeContainerModule::class
    ]
        )
interface GitHubImageSubcomponent {
    fun inject (gitHubImagePresenter: GitHubImagePresenter)
    fun inject (gitHubImageRepositoryImpl: GitHubImageRepositoryImpl)
}