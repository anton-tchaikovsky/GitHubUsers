package com.example.githubusers.domain.repository

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import io.reactivex.rxjava3.core.Single

interface IRepositoriesGitHubUserRepository {
    fun getRepositoriesGitHubUser(gitHubUser: GitHubUser): Single<List<RepositoryGitHubUser>>
}
