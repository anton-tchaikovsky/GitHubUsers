package com.example.githubusers.domain.repository.cache

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRepositoriesGitHubUserCache {
    fun saveToCache(gitHubUser: GitHubUser, repositoriesGitHubUser: List<RepositoryGitHubUser>):Completable
    fun readFromCache(gitHubUser: GitHubUser): Single<List<RepositoryGitHubUser>>
}
