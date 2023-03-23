package com.example.githubusers.domain.repository

import com.example.githubusers.domain.dto.GitHubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface GitHubUsersRepository {
    fun getGitHubUsers(): Single<List<GitHubUser>>
    fun getDefaultGitHubUsers(): Observable<List<GitHubUser>>
}
