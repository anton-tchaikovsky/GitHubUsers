package com.example.githubusers.ui.repositories_git_hub_user.repositories_git_hub_user_recycle_view

import com.example.githubusers.domain.dto.RepositoryGitHubUser

class ItemRepositoriesGitHubUserPresenterImpl(
    override val entityList: MutableList<RepositoryGitHubUser> = mutableListOf(),
):
    IItemRepositoriesGitHubUserPresenter {
    override var itemClickListener: ((IItemRepositoriesGitHubUserView) -> Unit)? = null

    override fun getCount(): Int =
        entityList.size

    override fun getId(itemPosition: Int): Int = entityList[itemPosition].id

    override fun bindView(itemView: IItemRepositoriesGitHubUserView) {
        itemView.itemPosition?.let{ it ->
            with(entityList[it]){
                itemView.showName(name)
                itemView.showId(id)
                itemClickListener?.let { clickListener ->
                    itemView.setClickListener(clickListener)
                }
                }
            }
        }
}