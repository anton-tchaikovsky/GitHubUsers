package com.example.githubusers.ui.git_hub_users

interface ItemGitHubUsersView {
    var itemPosition: Int?
    fun showAvatar(url: String)
    fun showLogin (login: String)
    fun showId (id: Int)
    fun setAvatarClickListener(avatarClickListener:((ItemGitHubUsersView)->Unit))
    fun setOpenRepositoriesButtonClickListener(openRepositoriesButtonClickListener:((ItemGitHubUsersView)->Unit))
}