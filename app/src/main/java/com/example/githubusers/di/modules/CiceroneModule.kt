package com.example.githubusers.di.modules

import com.example.githubusers.navigation.GitHubUsersScreensImpl
import com.example.githubusers.navigation.IGitHubUsersScreens
import com.example.githubusers.navigation.IRepositoriesGitHubUserScreens
import com.example.githubusers.navigation.RepositoriesGitHubUserScreensImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun router(): Router = cicerone.router

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()


    @Singleton
    @Provides
    fun gitHubUsersScreen(): IGitHubUsersScreens = GitHubUsersScreensImpl()

    @Singleton
    @Provides
    fun repositoriesGitHubUserScreen(): IRepositoriesGitHubUserScreens = RepositoriesGitHubUserScreensImpl()

}