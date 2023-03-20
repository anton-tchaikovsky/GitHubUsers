package com.example.githubusers.domain.repository

import com.example.githubusers.domain.dto.GitHubUser
import io.reactivex.rxjava3.core.Observable

interface GitHubUsersRepository {
    fun getGitHubUsers(
        onSuccess: (List<GitHubUser>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    fun getDefaultGitHubUsers(): Observable<List<GitHubUser>>

}
