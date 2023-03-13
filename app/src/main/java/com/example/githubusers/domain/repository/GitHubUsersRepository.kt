package com.example.githubusers.domain.repository

import com.example.githubusers.domain.dto.GitHubUser

interface GitHubUsersRepository {
    fun getGitHubUsers(
        onSuccess: (List<GitHubUser>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}