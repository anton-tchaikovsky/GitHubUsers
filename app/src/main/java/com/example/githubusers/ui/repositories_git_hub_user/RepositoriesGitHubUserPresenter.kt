package com.example.githubusers.ui.repositories_git_hub_user

import android.util.Log
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import moxy.MvpPresenter

class RepositoriesGitHubUserPresenter :
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
            Log.d("@@@", it.itemPosition.toString())
        }

        viewState.run {
            gitHubUser?.let {
                initView(it.login)
            }
            showRepositories()
        }
    }
}