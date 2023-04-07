package com.example.githubusers.ui.repositories_git_hub_user

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.navigation.IRepositoriesGitHubUserScreens
import com.example.githubusers.ui.repositories_git_hub_user.repositories_git_hub_user_recycle_view.IItemRepositoriesGitHubUserPresenter
import com.example.githubusers.ui.repositories_git_hub_user.repositories_git_hub_user_recycle_view.ItemRepositoriesGitHubUserPresenterImpl
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoriesGitHubUserPresenter:MvpPresenter<IRepositoriesGitHubUserView>() {

    @Inject
    lateinit var router:Router

    @Inject
    lateinit var gitHubUsersAppScreens: IRepositoriesGitHubUserScreens

    val itemRepositoriesGitHubUserPresenter: IItemRepositoriesGitHubUserPresenter =
        ItemRepositoriesGitHubUserPresenterImpl()
    var gitHubUser: GitHubUser? = null
    var repositoriesGitHubUser: List<RepositoryGitHubUser> = listOf()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        itemRepositoriesGitHubUserPresenter.entityList.apply {
            clear()
            addAll(repositoriesGitHubUser)
        }

        itemRepositoriesGitHubUserPresenter.itemClickListener = {
            router.navigateTo(gitHubUsersAppScreens.infoAboutRepositoryGitHubUserScreen(repositoriesGitHubUser[it.itemPosition!!]))
        }

        viewState.run {
            gitHubUser?.let {
                initView(it.login)
            }
            showRepositories()
        }
    }
}