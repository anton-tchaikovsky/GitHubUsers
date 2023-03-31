package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.domain.repository.GitHubRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import okhttp3.ResponseBody


class GitHubUsersPresenter(
    private val gitHubRepository: GitHubRepository, private val router: Router,
    private val gitHubUsersAppScreens: GitHubUsersAppScreens
) : MvpPresenter<GitHubUsersView>() {

    val itemGitHubUsersPresenter: ItemGitHubUsersPresenter = ItemGitHubUsersPresenterImpl()
    private lateinit var disposableDefaultGitHubUsers: Disposable
    private val observableDefaultGitHubUsers = gitHubRepository.getDefaultGitHubUsers()

    override fun onFirstViewAttach() {
        viewState.run {
            initView()
            checkPermissionWriteExternalStorage()
        }
        subscribeToDefaultGitHubUsers()
        super.onFirstViewAttach()
    }

    fun subscribeToLoadingGitHubImage() {
        gitHubRepository.loadGitHubImage()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    subscribeToSaveGitHubImageJpg(it)
                },
                onError = {
                    viewState.showError(it)
                }
            )
    }

    private fun subscribeToSaveGitHubImageJpg(responseBodyGitHubImage: ResponseBody) {
        gitHubRepository.saveGitHubImageJpg(responseBodyGitHubImage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { viewState.showError(it) }
            )
    }

    private fun subscribeToDefaultGitHubUsers() {
        viewState.showLoading()
        disposableDefaultGitHubUsers = observableDefaultGitHubUsers
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
        gitHubRepository.getGitHubUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
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
        itemGitHubUsersPresenter.entityList.apply {
            clear()
            addAll(gitHubUsersList)
        }
        viewState.run {
            showAnimateGitHubUsers()
            hideLoading()
            showGitHubUsers()
        }
        itemGitHubUsersPresenter.avatarClickListener = {
            itemGitHubUsersPresenter.entityList[it.itemPosition!!].run {
                openAvatarGitHubUserFragment(login, avatarUrl)
            }
        }

        itemGitHubUsersPresenter.openRepositoriesButtonClickListener = {
                subscribeToLoadingRepositoriesGitHubUser(itemGitHubUsersPresenter.entityList[it.itemPosition!!])
        }
    }

    private fun subscribeToLoadingRepositoriesGitHubUser(gitHubUser: GitHubUser) {
        gitHubRepository.getReposGitHubUser(gitHubUser.reposUrl)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    openRepositoriesGitHubUserFragment(gitHubUser, it)
                },
                onError = {
                    viewState.showError(it)
                }
            )
    }

    fun onRequestGitHubUsers() {
        disposableDefaultGitHubUsers.dispose()
        subscribeToLoadingGitHubUsers()
    }

    fun onRequestDefaultGitHubUsers() {
        disposableDefaultGitHubUsers.dispose()
        subscribeToDefaultGitHubUsers()
    }

    fun onRequestGitHubImage() {
        onOpenGitHubImageFragment()
    }

    private fun openAvatarGitHubUserFragment(login: String, avatarUrl: String) {
        router.navigateTo(gitHubUsersAppScreens.avatarGitHubUserScreen(login, avatarUrl))
    }

    private fun openRepositoriesGitHubUserFragment(gitHubUser:GitHubUser, repositoriesGitHubUser:List<RepositoryGitHubUser>) {
        router.navigateTo(gitHubUsersAppScreens.repositoriesGitHubUserScreen(gitHubUser, repositoriesGitHubUser))
    }

    private fun onOpenGitHubImageFragment() {
        router.navigateTo(gitHubUsersAppScreens.gitHubImageScreen())
    }

}