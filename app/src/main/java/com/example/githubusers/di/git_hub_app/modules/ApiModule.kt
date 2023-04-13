package com.example.githubusers.di.git_hub_app.modules

import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.data.api.IApiGitHubImage
import com.example.githubusers.data.api.IApiGitHubUsers
import com.example.githubusers.data.api.RemoteDataSourceGitHubImage
import com.example.githubusers.data.api.RemoteDataSourceGitHubUsers
import com.example.githubusers.data.repository.network.NetWorkStatus
import com.example.githubusers.domain.repository.network.INetWorkStatus
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun netWorkStatus(gitHubUsersApp: GitHubUsersApp): INetWorkStatus =
        NetWorkStatus(gitHubUsersApp)

    @Singleton
    @Provides
    fun remoteDataSourceGitHubUsers(apiGitHubUsers: IApiGitHubUsers): RemoteDataSourceGitHubUsers =
        RemoteDataSourceGitHubUsers(apiGitHubUsers)

    @Singleton
    @Provides
    fun apiGitHubUsers(@Named("baseUrlGitHubUsers") baseUrlGitHubUsers: String): IApiGitHubUsers =
        Retrofit.Builder()
            .baseUrl(baseUrlGitHubUsers)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(IApiGitHubUsers::class.java)

    @Named("baseUrlGitHubUsers")
    @Provides
    fun baseUrl(): String = BASE_URL_GIT_HUB_USERS

    @Singleton
    @Provides
    fun remoteDataSourceGitHubImage(apiGitHubImage: IApiGitHubImage): RemoteDataSourceGitHubImage =
        RemoteDataSourceGitHubImage(apiGitHubImage)

    @Singleton
    @Provides
    fun apiGitHubImage(@Named("baseUrlGitHubImage") baseUrlGitHubImage: String): IApiGitHubImage =
        Retrofit.Builder()
            .baseUrl(baseUrlGitHubImage)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(IApiGitHubImage::class.java)

    @Named("baseUrlGitHubImage")
    @Provides
    fun baseUrlGitHubImage(): String = BASE_URL_GIT_HUB_IMAGE

    companion object {
        private const val BASE_URL_GIT_HUB_IMAGE = "https://img2.freepng.ru/"
        private const val BASE_URL_GIT_HUB_USERS = "https://api.github.com/"
    }

}