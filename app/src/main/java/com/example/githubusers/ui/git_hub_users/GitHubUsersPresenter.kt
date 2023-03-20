package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.domain.repository.GitHubUsersRepository
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class GitHubUsersPresenter(
    private val gitHubUsersRepository: GitHubUsersRepository, private val router: Router,
    private val gitHubUsersAppScreens: GitHubUsersAppScreens
) : MvpPresenter<GitHubUsersView>() {

    val itemGitHubUsersPresenter: ItemGitHubUsersPresenter = ItemGitHubUsersPresenterImpl()


    override fun onFirstViewAttach() {
        viewState.initView()
        super.onFirstViewAttach()
    }

    fun onRequestGitHubUsers() {
        loadGitHubUsers()
    }

    private fun onOpenAvatarGitHubUsersFragment(login: String, avatarUrl: String) {
        router.navigateTo(gitHubUsersAppScreens.avatarGitHubUserScreen(login, avatarUrl))
    }

    fun onBackPressed() {
        router.exit()
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
                        itemGitHubUsersPresenter.gitHubUsersList[it.itemPosition!!].run {
                            onOpenAvatarGitHubUsersFragment(login, avatarUrl)
                        }
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



