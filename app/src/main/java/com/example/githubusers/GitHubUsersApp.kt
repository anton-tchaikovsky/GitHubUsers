package com.example.githubusers

import android.app.Application
import com.example.githubusers.data.room.DatabaseGitHubUsers
import com.example.githubusers.di.AppComponent
import com.example.githubusers.di.DaggerAppComponent
import com.example.githubusers.di.modules.AppModule

class GitHubUsersApp : Application() {

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