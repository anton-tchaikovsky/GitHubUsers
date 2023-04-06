package com.example.githubusers.ui.repositories_git_hub_user.repositories_git_hub_user_recycle_view

import com.example.githubusers.ui.recycle_view.IItemRecycleView

interface IItemRepositoriesGitHubUserView: IItemRecycleView {
    fun showName (name: String)
    fun showId (id: Int)
    fun setClickListener (itemClickListener:((IItemRepositoriesGitHubUserView)->Unit))
}