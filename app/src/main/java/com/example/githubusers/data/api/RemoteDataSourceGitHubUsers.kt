package com.example.githubusers.data.api

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.utils.BASE_URL_API_GIT_HUB_USERS
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSourceGitHubUsers {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_API_GIT_HUB_USERS)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    private val apiGitHubUsers = retrofit.create(APIGitHubUsers::class.java)

    fun callAPIGitHubUsers(callback: Callback<List<GitHubUser>>) {
        apiGitHubUsers.loadGitHubUsers().enqueue(callback)
    }
}