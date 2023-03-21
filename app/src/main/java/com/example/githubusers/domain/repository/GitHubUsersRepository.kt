package com.example.githubusers.domain.repository

import com.example.githubusers.domain.dto.GitHubUser
import io.reactivex.rxjava3.core.Observable

interface GitHubUsersRepository {
    fun getGitHubUsers(): Observable<List<GitHubUser>>
    fun getDefaultGitHubUsers(): Observable<List<GitHubUser>>
}
