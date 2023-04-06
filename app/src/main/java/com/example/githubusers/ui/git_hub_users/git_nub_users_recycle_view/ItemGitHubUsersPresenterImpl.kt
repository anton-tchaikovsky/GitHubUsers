package com.example.githubusers.ui.git_hub_users.git_nub_users_recycle_view

import com.example.githubusers.domain.dto.GitHubUser

class ItemGitHubUsersPresenterImpl(
    override val entityList: MutableList<GitHubUser> = mutableListOf()
) : IItemGitHubUsersPresenter {

    override var avatarClickListener: ((IItemGitHubUsersView) -> Unit)? = null
    override var openRepositoriesButtonClickListener: ((IItemGitHubUsersView) -> Unit)? = null

    override fun getCount(): Int = entityList.size

    override fun getId(itemPosition: Int): Int = entityList[itemPosition].id

    override fun bindView(itemView: IItemGitHubUsersView) {
        itemView.itemPosition?.let { it ->
            with(entityList[it]) {
                itemView.showAvatar(avatarUrl)
                itemView.showLogin(login)
                itemView.showId(id)
                avatarClickListener?.let { clickListener ->
                    itemView.setAvatarClickListener(clickListener)
                }
                openRepositoriesButtonClickListener?.let { clickListener ->
                    itemView.setOpenRepositoriesButtonClickListener(clickListener)
                }
            }
        }
    }

}