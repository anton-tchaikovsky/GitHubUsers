package com.example.githubusers.ui.repositories_git_hub_user

import com.example.githubusers.di.repositories_git_hub_user.IRepositoriesGitHubUserScopeContainer
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.IRepositoriesGitHubUserRepository
import com.example.githubusers.navigation.IRepositoriesGitHubUserScreens
import com.example.githubusers.ui.repositories_git_hub_user.repositories_git_hub_user_recycle_view.IItemRepositoriesGitHubUserPresenter
import com.example.githubusers.ui.repositories_git_hub_user.repositories_git_hub_user_recycle_view.ItemRepositoriesGitHubUserPresenterImpl
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoriesGitHubUserPresenter:MvpPresenter<IRepositoriesGitHubUserView>() {

    @Inject
    lateinit var repositoriesGitHubUserRepository: IRepositoriesGitHubUserRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var gitHubUsersAppScreens: IRepositoriesGitHubUserScreens

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var repositoriesGitHubUserScopeContainer: IRepositoriesGitHubUserScopeContainer

    val itemRepositoriesGitHubUserPresenter: IItemRepositoriesGitHubUserPresenter =
        ItemRepositoriesGitHubUserPresenterImpl()

    var gitHubUser: GitHubUser? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        gitHubUser?.let {
            viewState.initView(it.login)
            subscribeToLoadingRepositoriesGitHubUser(it)
        }
    }

    private fun subscribeToLoadingRepositoriesGitHubUser(gitHubUser: GitHubUser) {
        repositoriesGitHubUserRepository.getRepositoriesGitHubUser(gitHubUser)
            .observeOn(mainThreadScheduler)
            .subscribeBy(
                onSuccess = { repositoriesGitHubUser ->
                    itemRepositoriesGitHubUserPresenter.entityList.apply {
                        clear()
                        addAll(repositoriesGitHubUser)
                    }

                    itemRepositoriesGitHubUserPresenter.itemClickListener = {
                        router.navigateTo(
                            gitHubUsersAppScreens.infoAboutRepositoryGitHubUserScreen(
                                repositoriesGitHubUser[it.itemPosition!!]
                            )
                        )
                    }

                    viewState.showRepositories()
                },
                onError = {
                    viewState.showError(it)
                }
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        repositoriesGitHubUserScopeContainer.releaseRepositoriesGitHubUserScope()
    }
}