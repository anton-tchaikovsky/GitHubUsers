package com.example.githubusers.ui.git_hub_users.git_nub_users_recycle_view

import com.example.githubusers.ui.recycle_view.IItemRecycleView

interface IItemGitHubUsersView: IItemRecycleView {
    fun showAvatar(url: String)
    fun showLogin (login: String)
    fun showId (id: Int)
    fun setAvatarClickListener(avatarClickListener:((IItemGitHubUsersView)->Unit))
    fun setOpenRepositoriesButtonClickListener(openRepositoriesButtonClickListener:((IItemGitHubUsersView)->Unit))
}