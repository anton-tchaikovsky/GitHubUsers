package com.example.githubusers.ui.avatar_git_hub_user

import moxy.MvpPresenter

class AvatarGitHubUserPresenter:
    MvpPresenter<IAvatarGitHubUserView>() {

    var login: String?=null
    var avatarUrl: String? = null

     fun onInitView() {
        if (login != null && avatarUrl != null)
            viewState.showAvatar(login!!, avatarUrl!!)
         else
             viewState.showEmptyGitHubUser()
    }
}