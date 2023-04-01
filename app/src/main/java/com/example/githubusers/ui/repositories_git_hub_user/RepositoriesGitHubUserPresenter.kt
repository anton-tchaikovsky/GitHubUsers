package com.example.githubusers.ui.repositories_git_hub_user

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.ui.git_hub_users.GitHubUsersAppScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoriesGitHubUserPresenter(
    private val router: Router,
    private val gitHubUsersAppScreens: GitHubUsersAppScreens
) :
    MvpPresenter<RepositoriesGitHubUserView>() {
    val itemRepositoriesGitHubUserPresenter: ItemRepositoriesGitHubUserPresenter =
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