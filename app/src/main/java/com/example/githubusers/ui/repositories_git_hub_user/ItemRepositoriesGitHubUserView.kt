package com.example.githubusers.ui.repositories_git_hub_user

import com.example.githubusers.ui.git_hub_users.ItemRecycleView

interface ItemRepositoriesGitHubUserView:ItemRecycleView {
    fun showName (name: String)
    fun showId (id: Int)
    fun setClickListener (itemClickListener:((ItemRepositoriesGitHubUserView)->Unit))
}