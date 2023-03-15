package com.example.githubusers.ui

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.githubusers.databinding.ItemGitHubUsersRecycleViewBinding

class GitHubUsersViewHolder
    (private val binding: ItemGitHubUsersRecycleViewBinding):
    RecyclerView.ViewHolder(binding.root), ItemGitHubUsersView {

    override fun showAvatar(url: String) {
        binding.gitHubUsersAvatarImageView.load(url)
    }

    override fun showLogin(login: String) {
        binding.gitHubUserLogin.text = login
    }

    override fun showId(id: Int) {
        binding.gitHubUserId.text = id.toString()
    }
}