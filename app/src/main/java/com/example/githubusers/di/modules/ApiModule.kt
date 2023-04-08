package com.example.githubusers.di.modules

import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.data.repository.network.NetWorkStatus
import com.example.githubusers.domain.repository.network.INetWorkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun netWorkStatus(gitHubUsersApp: GitHubUsersApp):INetWorkStatus = NetWorkStatus(gitHubUsersApp)

}