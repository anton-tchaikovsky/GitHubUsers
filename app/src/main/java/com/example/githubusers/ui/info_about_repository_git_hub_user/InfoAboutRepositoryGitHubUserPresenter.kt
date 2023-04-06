package com.example.githubusers.ui.info_about_repository_git_hub_user

import com.example.githubusers.domain.dto.RepositoryGitHubUser
import moxy.MvpPresenter

class InfoAboutRepositoryGitHubUserPresenter :
    MvpPresenter<IInfoAboutRepositoryGitHubUserView>() {
    var repositoryGitHubUser: RepositoryGitHubUser? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        with(repositoryGitHubUser) {
            if(this!=null)
                viewState.initView(name, language?: "", stargazersCount, watchersCount, forksCount)
        }
    }

}