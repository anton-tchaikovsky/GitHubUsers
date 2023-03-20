package com.example.githubusers.ui.git_hub_users

import android.util.Log
import com.example.githubusers.domain.repository.GitHubUsersRepository
import com.example.githubusers.utils.DEFAULT_GIT_HAB_USERS_LIST
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class GitHubUsersPresenter(
    private val gitHubUsersRepository: GitHubUsersRepository, private val router: Router,
    private val gitHubUsersAppScreens: GitHubUsersAppScreens
) : MvpPresenter<GitHubUsersView>() {

    val itemGitHubUsersPresenter: ItemGitHubUsersPresenter = ItemGitHubUsersPresenterImpl()
    private lateinit var disposableInterval: Disposable

    override fun onFirstViewAttach() {
        viewState.initView()
        subscribeToInterval()
        super.onFirstViewAttach()
    }

    private fun subscribeToInterval() {
       disposableInterval = gitHubUsersRepository.getObservableInterval()
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeBy(
            onNext = {
                Log.v("@@@", "$it.toString()")
                subscribeToDefaultGitHubUsers(it)
            },
           onError = {
               Log.v("@@@", "onErrorInterval: ${it.message}")
               viewState.showError(it)
           }
        )
    }

    private fun subscribeToDefaultGitHubUsers(interval: Long) {
        gitHubUsersRepository.getObservableGitHubUsers(interval).subscribeBy(
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
                if (interval== DEFAULT_GIT_HAB_USERS_LIST.lastIndex.toLong())
                    disposableInterval.dispose()
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
        disposableInterval.dispose()
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



