package com.example.githubusers.ui

interface ItemGitHubUsersView {
    var itemPosition: Int?
    fun showAvatar(url: String)
    fun showLogin (login: String)
    fun showId (id: Int)
    fun setItemGitHubUsersClickListener(itemGitHubUsersClickListener:((ItemGitHubUsersView)->Unit))
}