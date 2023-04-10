package com.example.githubusers.di.modules

import com.example.githubusers.GitHubUsersApp
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule (val gitHubUsersApp: GitHubUsersApp) {

    @Provides
    fun gitHubUserApp(): GitHubUsersApp = gitHubUsersApp

    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

}