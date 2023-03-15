package com.example.githubusers.ui

import com.example.githubusers.domain.dto.GitHubUser

interface ItemGitHubUsersPresenter {
    val gitHubUsersList:MutableList<GitHubUser>
    fun getCount():Int
    fun getId(itemPosition:Int):Int
    fun bindView(itemView: ItemGitHubUsersView, itemPosition:Int)
}