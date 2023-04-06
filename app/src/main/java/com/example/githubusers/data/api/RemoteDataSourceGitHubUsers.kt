package com.example.githubusers.data.api

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.utils.BASE_URL_API_GIT_HUB_USERS
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSourceGitHubUsers {

    private val retrofitGitHubUsers: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_API_GIT_HUB_USERS)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val apiGitHubUsers = retrofitGitHubUsers.create(IApiIGitHubUsers::class.java)

    fun callAPIGitHubUsers():Single<List<GitHubUser>> =
        apiGitHubUsers.loadGitHubUsers()

    fun callAPIRepositoriesGitHubUser(repoUrl: String): Single<List<RepositoryGitHubUser>> =
        apiGitHubUsers.loadRepositoriesGitHubUser(repoUrl)

}