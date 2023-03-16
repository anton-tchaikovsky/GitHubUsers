package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.domain.dto.GitHubUser

class ItemGitHubUsersPresenterImpl(override val gitHubUsersList: MutableList<GitHubUser> = mutableListOf()):
    ItemGitHubUsersPresenter {

    override var itemGitHubUsersClickListener: ((ItemGitHubUsersView) -> Unit)? = null

    override fun getCount(): Int = gitHubUsersList.size

    override fun getId(itemPosition: Int): Int = gitHubUsersList[itemPosition].id

    override fun bindView(itemView: ItemGitHubUsersView) {
        itemView.itemPosition?.let{ it ->
            with(gitHubUsersList[it]){
                itemView.showAvatar(avatarUrl)
                itemView.showLogin(login)
                itemView.showId(id)
                itemGitHubUsersClickListener?.let { clickListener ->
                    itemView.setItemGitHubUsersClickListener(clickListener) }
            }
        }
    }

}