package com.example.githubusers

import android.app.Application
import com.example.githubusers.data.repository.GitHubImageRepositoryImpl
import com.example.githubusers.data.repository.GitHubUsersRepositoryImpl
import com.example.githubusers.data.repository.RepositoriesGitHubUserRepositoryImpl
import com.example.githubusers.data.repository.network.NetWorkStatus
import com.example.githubusers.data.room.DatabaseGitHubUsers
import com.example.githubusers.di.AppComponent
import com.example.githubusers.di.DaggerAppComponent
import com.example.githubusers.di.modules.AppModule
import com.example.githubusers.domain.repository.IGitHubImageRepository
import com.example.githubusers.domain.repository.IGitHubUsersRepository
import com.example.githubusers.domain.repository.IRepositoriesGitHubUserRepository

class GitHubUsersApp : Application() {

    val gitHubUsersRepository: IGitHubUsersRepository by lazy {
        GitHubUsersRepositoryImpl(NetWorkStatus(this))
    }

    val repositoriesGitHubUserRepository: IRepositoriesGitHubUserRepository by lazy {
        RepositoriesGitHubUserRepositoryImpl(NetWorkStatus(this))
    }

    val gitHubImageRepository: IGitHubImageRepository by lazy {
        GitHubImageRepositoryImpl()
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        DatabaseGitHubUsers.createInstanceDatabase(this)
    }

    companion object{
        lateinit var instance: GitHubUsersApp
    }

}