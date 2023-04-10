package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.domain.repository.IGitHubImageRepository
import com.example.githubusers.domain.repository.IGitHubUsersRepository
import com.example.githubusers.domain.repository.IRepositoriesGitHubUserRepository
import com.example.githubusers.navigation.IGitHubUsersScreens
import com.example.githubusers.ui.git_hub_users.git_nub_users_recycle_view.IItemGitHubUsersPresenter
import com.example.githubusers.ui.git_hub_users.git_nub_users_recycle_view.ItemGitHubUsersPresenterImpl
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import okhttp3.ResponseBody
import javax.inject.Inject


class GitHubUsersPresenter: MvpPresenter<IGitHubUsersView>() {

    @Inject
    lateinit var gitHubUsersRepository: IGitHubUsersRepository

    @Inject
    lateinit var repositoriesGitHubUserRepository: IRepositoriesGitHubUserRepository

    @Inject
    lateinit var gitHubImageRepository: IGitHubImageRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var gitHubUsersAppScreens: IGitHubUsersScreens

    @Inject
    lateinit var mainThreadScheduler:Scheduler

    val itemGitHubUsersPresenter: IItemGitHubUsersPresenter = ItemGitHubUsersPresenterImpl()
    private lateinit var disposableDefaultGitHubUsers: Disposable

    override fun onFirstViewAttach() {
        viewState.run {
            initView()
            checkPermissionWriteExternalStorage()
        }
        subscribeToDefaultGitHubUsers()
        super.onFirstViewAttach()
    }

    fun subscribeToLoadingGitHubImage() {
        gitHubImageRepository.loadGitHubImage()
            .observeOn(mainThreadScheduler)
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
        gitHubImageRepository.saveGitHubImageJpg(responseBodyGitHubImage)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThreadScheduler)
            .subscribeBy(
                onError = { viewState.showError(it) }
            )
    }

    private fun subscribeToDefaultGitHubUsers() {
        viewState.showLoading()
        disposableDefaultGitHubUsers = gitHubUsersRepository.getDefaultGitHubUsers()
            .observeOn(mainThreadScheduler)
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
            .observeOn(mainThreadScheduler)
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
        repositoriesGitHubUserRepository.getRepositoriesGitHubUser(gitHubUser)
            .observeOn(mainThreadScheduler)
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