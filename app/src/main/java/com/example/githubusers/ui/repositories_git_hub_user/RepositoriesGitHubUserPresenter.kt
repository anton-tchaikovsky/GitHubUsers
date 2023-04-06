package com.example.githubusers.ui.repositories_git_hub_user

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.navigation.IGitHubUsersAppScreens
import com.example.githubusers.ui.repositories_git_hub_user.repositories_git_hub_user_recycle_view.IItemRepositoriesGitHubUserPresenter
import com.example.githubusers.ui.repositories_git_hub_user.repositories_git_hub_user_recycle_view.ItemRepositoriesGitHubUserPresenterImpl
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoriesGitHubUserPresenter(
    private val router: Router,
    private val gitHubUsersAppScreens: IGitHubUsersAppScreens
) :
    MvpPresenter<IRepositoriesGitHubUserView>() {
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