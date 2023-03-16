package com.example.githubusers.ui.git_hub_users

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.githubusers.databinding.ItemGitHubUsersRecycleViewBinding

class ItemGitHubUsersViewHolder
    (private val binding: ItemGitHubUsersRecycleViewBinding):
    RecyclerView.ViewHolder(binding.root), ItemGitHubUsersView {

    override var itemPosition: Int? = null

    override fun showAvatar(url: String) {
        binding.gitHubUsersAvatarImageView.load(url)
    }

    override fun showLogin(login: String) {
        binding.gitHubUserLogin.text = login
    }

    override fun showId(id: Int) {
        binding.gitHubUserId.text = id.toString()
    }

    override fun setItemGitHubUsersClickListener(itemGitHubUsersClickListener:((ItemGitHubUsersView)->Unit)){
        itemView.setOnClickListener {
            itemGitHubUsersClickListener(this@ItemGitHubUsersViewHolder)
        }
    }

}