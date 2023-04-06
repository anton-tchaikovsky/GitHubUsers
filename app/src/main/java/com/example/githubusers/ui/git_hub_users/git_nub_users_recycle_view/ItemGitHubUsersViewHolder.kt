package com.example.githubusers.ui.git_hub_users.git_nub_users_recycle_view

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.ItemGitHubUsersRecycleViewBinding
import com.example.githubusers.ui.image.IImageLoader

class ItemGitHubUsersViewHolder
    (
    private val binding: ItemGitHubUsersRecycleViewBinding,
    private val imageLoader: IImageLoader<AppCompatImageView>
) :
    RecyclerView.ViewHolder(binding.root), IItemGitHubUsersView {

    override var itemPosition: Int? = null

    override fun showAvatar(url: String) {
        imageLoader.loadImageInto(url, binding.gitHubUserAvatarImageView)
    }

    override fun showLogin(login: String) {
        binding.gitHubUserLogin.text = login
    }

    override fun showId(id: Int) {
        binding.gitHubUserId.text = id.toString()
    }

    override fun setAvatarClickListener(avatarClickListener:((IItemGitHubUsersView)->Unit)){
        binding.gitHubUserAvatarImageView.setOnClickListener {
            avatarClickListener(this@ItemGitHubUsersViewHolder)
        }
    }

    override fun setOpenRepositoriesButtonClickListener(openRepositoriesButtonClickListener: (IItemGitHubUsersView) -> Unit) {
        binding.openRepositoriesButton.setOnClickListener {
            openRepositoriesButtonClickListener(this@ItemGitHubUsersViewHolder)
        }
    }

}