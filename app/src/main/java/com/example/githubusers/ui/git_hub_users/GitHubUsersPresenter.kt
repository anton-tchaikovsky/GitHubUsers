package com.example.githubusers.ui.git_hub_users

import android.util.Log
import com.example.githubusers.domain.repository.GitHubUsersRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class GitHubUsersPresenter(
    private val gitHubUsersRepository: GitHubUsersRepository, private val router: Router,
    private val gitHubUsersAppScreens: GitHubUsersAppScreens
) : MvpPresenter<GitHubUsersView>() {

    val itemGitHubUsersPresenter: ItemGitHubUsersPresenter = ItemGitHubUsersPresenterImpl()


    override fun onFirstViewAttach() {
        viewState.initView()
        subscribeToDefaultGitHubUsers()
        super.onFirstViewAttach()
    }

    private fun subscribeToDefaultGitHubUsers() {
        gitHubUsersRepository.getObservable().subscribeBy(
            onNext = { gitHubUsers ->
                Log.v("@@@", "onNext: $gitHubUsers")
                itemGitHubUsersPresenter.gitHubUsersList.apply {
                    clear()
                    addAll(gitHubUsers)
                }
                viewState.showGitHubUsers()
                itemGitHubUsersPresenter.itemGitHubUsersClickListener = {
                    itemGitHubUsersPresenter.gitHubUsersList[it.itemPosition!!].run {
                        onOpenAvatarGitHubUsersFragment(login, avatarUrl)
                    }
                }
            },
            onError = {
                Log.v("@@@", "onError: ${it.message}")
                viewState.showError(it)
            },
            onComplete = {
                Log.v("@@@", "onComplete")
            }
        )
    }

    fun onRequestGitHubUsers() {
        loadGitHubUsers()
    }

    private fun onOpenAvatarGitHubUsersFragment(login: String, avatarUrl: String) {
        router.navigateTo(gitHubUsersAppScreens.avatarGitHubUserScreen(login, avatarUrl))
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



