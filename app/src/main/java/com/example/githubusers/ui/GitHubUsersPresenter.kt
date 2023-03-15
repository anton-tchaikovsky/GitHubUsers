package com.example.githubusers.ui

import com.example.githubusers.domain.repository.GitHubUsersRepository
import moxy.MvpPresenter

class GitHubUsersPresenter(private val gitHubUsersRepository: GitHubUsersRepository) :
    MvpPresenter<GitHubUsersView>() {

    val itemGitHubUsersPresenter: ItemGitHubUsersPresenter = ItemGitHubPresenterImpl()

    override fun onFirstViewAttach() {
        viewState.initView()
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
                    itemGitHubUsersPresenter.gitHubUsersList.apply {
                        clear()
                        addAll(it)
                    }
                    showAnimateGitHubUsers()
                    hideLoading()
                    showGitHubUsers()
                    itemGitHubUsersPresenter.itemGitHubUsersClickListener = {
                        showItemGitHubUsers(itemGitHubUsersPresenter.gitHubUsersList[it.itemPosition!!].login)
                    }
                },
                onError = {
                    hideLoading()
                    showError(it)
                }
            )
        }
    }
}



