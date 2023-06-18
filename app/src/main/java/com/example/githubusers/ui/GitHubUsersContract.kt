package com.example.githubusers.ui

import com.example.githubusers.domain.dto.GitHubUser

interface GitHubUsersContract {

    interface GitHubUsersView{
        fun showGitHubUsers(gitHubUsers: List<GitHubUser>)
        fun showError(error: Throwable)
        fun showLoading(isShowLoading: Boolean)
    }

    interface GitHubUsersPresenter{
        fun attach(gitHubUsersView:GitHubUsersView)
        fun detach()
        fun onRequestGitHubUsers()
    }
}