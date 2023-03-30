package com.example.githubusers.data.api

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoriesGitHubUser
import com.example.githubusers.utils.END_POINT_API_GIT_HUB_USERS
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface APIGitHubUsers {
    @GET(END_POINT_API_GIT_HUB_USERS)
    fun loadGitHubUsers(): Single<List<GitHubUser>>

    @GET
    fun loadRepositoriesGitHubUser(@Url repoUrl:String):Single<List<RepositoriesGitHubUser>>
}