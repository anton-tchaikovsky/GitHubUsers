package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.domain.repository.GitHubUsersRepository
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import moxy.MvpPresenter

class GitHubUsersPresenter(private val gitHubUsersRepository: GitHubUsersRepository, private val router: Router) :
    MvpPresenter<GitHubUsersView>() {

    val itemGitHubUsersPresenter: ItemGitHubUsersPresenter = ItemGitHubUsersPresenterImpl()

    override fun onFirstViewAttach() {
        viewState.initView()
        super.onFirstViewAttach()
    }

    fun onRequestGitHubUsers() {
        loadGitHubUsers()
    }

    fun onOpenItemGitHubUsersFragment(screenItemGitHubUsers: Screen){
        router.navigateTo(screenItemGitHubUsers)
    }

    fun onBackPressed(){
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
                            showItemGitHubUsers(login, avatarUrl)
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



