package com.example.githubusers.data.repository

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubUsersRepository

class GitHubUsersRepositoryImpl:GitHubUsersRepository {
    override fun getGitHubUsers(
        onSuccess: (List<GitHubUser>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        TODO("Not yet implemented")
    }
}