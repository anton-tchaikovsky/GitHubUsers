package com.example.githubusers.data.room

import com.example.githubusers.domain.dto.GitHubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGitHubUsersCache {
    val databaseGitHubUsers:DatabaseGitHubUsers
    fun saveToCache(gitHubUsers: List<GitHubUser>):Completable
    fun readFromCache(): Single<List<GitHubUser>>
}
