package com.example.githubusers.di.modules

import com.example.githubusers.GitHubUsersApp
import dagger.Module
import dagger.Provides

@Module
class AppModule (val gitHubUsersApp: GitHubUsersApp) {

    @Provides
    fun gitHubUserApp(): GitHubUsersApp = gitHubUsersApp

}