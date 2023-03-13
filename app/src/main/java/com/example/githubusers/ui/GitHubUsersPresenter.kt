package com.example.githubusers.ui

import com.example.githubusers.domain.repository.GitHubUsersRepository

class GitHubUsersPresenter (private val gitHubUsersRepository: GitHubUsersRepository) : GitHubUsersContract.GitHubUsersPresenter {

    private var gitHubUsersView: GitHubUsersContract.GitHubUsersView? = null

    override fun attach(gitHubUsersView: GitHubUsersContract.GitHubUsersView) {
        this.gitHubUsersView = gitHubUsersView
    }

    override fun detach() {
        gitHubUsersView = null
    }

    override fun onRequestGitHubUsers() {
        loadGitHubUsers()
    }

    private fun loadGitHubUsers() {
        gitHubUsersView?.run {
            showLoading(true)
            gitHubUsersRepository.getGitHubUsers(
                onSuccess = {
                    showLoading(false)
                    showGitHubUsers(it)
                },
                onError = {
                    showLoading(false)
                    showError(it)
                }
            )
        }
    }
}


