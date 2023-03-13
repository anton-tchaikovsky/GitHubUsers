package com.example.githubusers.ui

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubUsersRepository

class GitHubUsersPresenter (private val gitHubUsersRepository: GitHubUsersRepository) : GitHubUsersContract.GitHubUsersPresenter {

    private var gitHubUsersView: GitHubUsersContract.GitHubUsersView? = null
    private var gitHubUsersList: List<GitHubUser>? = null
    private var isShowLoading: Boolean? = null

    override fun attach(gitHubUsersView: GitHubUsersContract.GitHubUsersView) {
        this.gitHubUsersView = gitHubUsersView
        gitHubUsersView.run {
            gitHubUsersList?.let {
                showGitHubUsers(it)
            }
            isShowLoading?.let {
                showLoading(it)
            }
        }
    }

    override fun detach() {
        gitHubUsersView = null
    }

    override fun onRequestGitHubUsers() {
        loadGitHubUsers()
    }

    private fun loadGitHubUsers() {
        gitHubUsersView?.run {
            isShowLoading = true.also {
                showLoading(it)
            }
            gitHubUsersRepository.getGitHubUsers(
                onSuccess = {
                    isShowLoading = false.also { isShowLoading ->
                        showLoading(isShowLoading)
                    }
                    gitHubUsersList = it.also { gitHubUsersList ->
                        showGitHubUsers(gitHubUsersList)
                    }
                },
                onError = {
                    isShowLoading = false.also { isShowLoading ->
                        showLoading(isShowLoading)
                    }
                    showError(it)
                }
            )
        }
    }
}


