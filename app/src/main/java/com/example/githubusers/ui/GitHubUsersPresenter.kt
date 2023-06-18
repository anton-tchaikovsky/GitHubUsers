package com.example.githubusers.ui

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubUsersRepository

class GitHubUsersPresenter (private val gitHubUsersRepository: GitHubUsersRepository) : GitHubUsersContract.GitHubUsersPresenter {

    internal var gitHubUsersView: GitHubUsersContract.GitHubUsersView? = null
    internal var gitHubUsersList: List<GitHubUser>? = null
    internal var isShowLoading: Boolean? = null

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
                    onSuccessLoadingGitHubUsers(it)
                },
                onError = {
                   onErrorLoadingGitHubUsers(it)
                }
            )
        }
    }

    internal fun onSuccessLoadingGitHubUsers(gitHubUsersList: List<GitHubUser>){
        isShowLoading = false.also { isShowLoading ->
            gitHubUsersView?.showLoading(isShowLoading)
        }
        this.gitHubUsersList = gitHubUsersList.also {
            gitHubUsersView?.showGitHubUsers(it)
        }
    }

    internal fun onErrorLoadingGitHubUsers(error: Throwable){
        isShowLoading = false.also { isShowLoading ->
            gitHubUsersView?.showLoading(isShowLoading)
        }
        gitHubUsersView?.showError(error)
    }
}


