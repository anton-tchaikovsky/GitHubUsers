package com.example.githubusers.data.api

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.utils.END_POINT_API_GIT_HUB_USERS
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface APIGitHubUsers {
    @GET(END_POINT_API_GIT_HUB_USERS)
    fun loadGitHubUsers(): Single<List<GitHubUser>>
}