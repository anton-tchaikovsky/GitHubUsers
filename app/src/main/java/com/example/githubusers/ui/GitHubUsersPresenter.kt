package com.example.githubusers.ui

import com.example.githubusers.domain.repository.GitHubUsersRepository
import moxy.MvpPresenter

class GitHubUsersPresenter(private val gitHubUsersRepository: GitHubUsersRepository) :
    MvpPresenter<GitHubUsersView>() {

    override fun onFirstViewAttach() {
        loadGitHubUsers()
        super.onFirstViewAttach()
    }

    fun onRequestGitHubUsers() {
        loadGitHubUsers()
    }

    private fun loadGitHubUsers() {
        viewState.run {
            showLoading()
            gitHubUsersRepository.getGitHubUsers(
                onSuccess = {
                    showAnimateGitHubUsers()
                    hideLoading()
                    showGitHubUsers(it)
                },
                onError = {
                    hideLoading()
                    showError(it)
                }
            )
        }
    }
}



