package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.domain.dto.GitHubUser

interface ItemGitHubUsersPresenter {
    val gitHubUsersList:MutableList<GitHubUser>
    var avatarClickListener: ((ItemGitHubUsersView)->Unit)?
    var openRepositoriesButtonClickListener: ((ItemGitHubUsersView)->Unit)?
    fun getCount():Int
    fun getId(itemPosition:Int):Int
    fun bindView(itemView: ItemGitHubUsersView)
}