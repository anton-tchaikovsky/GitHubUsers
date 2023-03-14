package com.example.githubusers.ui

import com.example.githubusers.domain.repository.GitHubUsersRepository
import moxy.MvpPresenter

class GitHubUsersPresenter(private val gitHubUsersRepository: GitHubUsersRepository) :
    MvpPresenter<GitHubUsersView>() {

    fun onRequestGitHubUsers() {
        loadGitHubUsers()
    }

    private fun loadGitHubUsers() {
        viewState.run {
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



