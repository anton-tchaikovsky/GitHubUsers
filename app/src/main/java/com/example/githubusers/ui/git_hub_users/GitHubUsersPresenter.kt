package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubUsersRepository
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
    private lateinit var disposableDefaultGitHubUsers: Disposable

    override fun onFirstViewAttach() {
        viewState.initView()
        subscribeToDefaultGitHubUsers()
        super.onFirstViewAttach()
    }

    private fun subscribeToDefaultGitHubUsers() {
        viewState.showLoading()
        disposableDefaultGitHubUsers = gitHubUsersRepository.getDefaultGitHubUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { gitHubUsers ->
                    onSuccessLoadingGitHubUsers(gitHubUsers)
                },
                onError = {
                    onErrorLoadingGitHubUsers(it)
                }
            )
    }

    private fun subscribeToLoadingGitHubUsers() {
        viewState.showLoading()
        gitHubUsersRepository.getGitHubUsers()
            .subscribeBy(
                onNext = {
                    onSuccessLoadingGitHubUsers(it)
                },
                onError = {
                    onErrorLoadingGitHubUsers(it)
                }
            )
    }

    private fun onErrorLoadingGitHubUsers(error: Throwable) {
        viewState.run {
            hideLoading()
            showError(error)
        }
    }

    private fun onSuccessLoadingGitHubUsers(gitHubUsersList: List<GitHubUser>) {
        itemGitHubUsersPresenter.gitHubUsersList.apply {
            clear()
            addAll(gitHubUsersList)
        }
        viewState.run {
            showAnimateGitHubUsers()
            hideLoading()
            showGitHubUsers()
        }
        itemGitHubUsersPresenter.itemGitHubUsersClickListener = {
            itemGitHubUsersPresenter.gitHubUsersList[it.itemPosition!!].run {
                onOpenAvatarGitHubUsersFragment(login, avatarUrl)
            }
        }
    }

    fun onRequestGitHubUsers() {
        disposableDefaultGitHubUsers.dispose()
        subscribeToLoadingGitHubUsers()
    }

    private fun onOpenAvatarGitHubUsersFragment(login: String, avatarUrl: String) {
        router.navigateTo(gitHubUsersAppScreens.avatarGitHubUserScreen(login, avatarUrl))
    }

}