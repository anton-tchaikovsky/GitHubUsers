package com.example.githubusers.ui

import com.example.githubusers.domain.dto.GitHubUser

class ItemGitHubPresenterImpl(override val gitHubUsersList: MutableList<GitHubUser> = mutableListOf()): ItemGitHubUsersPresenter {

    override fun getCount(): Int = gitHubUsersList.size

    override fun getId(itemPosition: Int): Int = gitHubUsersList[itemPosition].id

    override fun bindView(itemView: ItemGitHubUsersView, itemPosition: Int) {
        with(gitHubUsersList[itemPosition]){
            itemView.showAvatar(avatarUrl)
            itemView.showLogin(login)
            itemView.showId(id)
        }
    }

}