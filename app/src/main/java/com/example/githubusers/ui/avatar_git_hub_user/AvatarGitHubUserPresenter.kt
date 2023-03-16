package com.example.githubusers.ui.avatar_git_hub_user

import moxy.MvpPresenter

class AvatarGitHubUserPresenter: MvpPresenter<AvatarGitHubUserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showAvatar()
    }
}