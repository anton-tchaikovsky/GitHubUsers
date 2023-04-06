package com.example.githubusers.domain.repository.cache

import com.example.githubusers.domain.dto.GitHubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGitHubUsersCache {
    fun saveToCache(gitHubUsers: List<GitHubUser>):Completable
    fun readFromCache(): Single<List<GitHubUser>>
}
