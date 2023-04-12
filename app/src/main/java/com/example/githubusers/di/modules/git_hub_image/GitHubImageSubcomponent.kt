package com.example.githubusers.di.modules.git_hub_image

import com.example.githubusers.data.repository.git_hub_image.GitHubImageRepositoryImpl
import com.example.githubusers.di.modules.git_hub_image.module.RepositoryModule
import com.example.githubusers.di.modules.git_hub_image.module.ScopeContainerModule
import com.example.githubusers.di.modules.git_hub_image.module.StorageDirectoryModule
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