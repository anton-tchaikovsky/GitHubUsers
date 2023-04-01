package com.example.githubusers.ui.git_hub_users

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.ItemGitHubUsersRecycleViewBinding

class ItemGitHubUsersViewHolder
    (
    private val binding: ItemGitHubUsersRecycleViewBinding,
    private val imageLoader: ImageLoader<AppCompatImageView>
) :
    RecyclerView.ViewHolder(binding.root), ItemGitHubUsersView {

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

    override fun setAvatarClickListener(avatarClickListener:((ItemGitHubUsersView)->Unit)){
        binding.gitHubUserAvatarImageView.setOnClickListener {
            avatarClickListener(this@ItemGitHubUsersViewHolder)
        }
    }

    override fun setOpenRepositoriesButtonClickListener(openRepositoriesButtonClickListener: (ItemGitHubUsersView) -> Unit) {
        binding.openRepositoriesButton.setOnClickListener {
            openRepositoriesButtonClickListener(this@ItemGitHubUsersViewHolder)
        }
    }

}