package com.example.githubusers.data.api

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import io.reactivex.rxjava3.core.Single

class RemoteDataSourceGitHubUsers (private val apiGitHubUsers: IApiGitHubUsers) {

    fun callAPIGitHubUsers():Single<List<GitHubUser>> =
        apiGitHubUsers.loadGitHubUsers()

    fun callAPIRepositoriesGitHubUser(repoUrl: String): Single<List<RepositoryGitHubUser>> =
        apiGitHubUsers.loadRepositoriesGitHubUser(repoUrl)

}