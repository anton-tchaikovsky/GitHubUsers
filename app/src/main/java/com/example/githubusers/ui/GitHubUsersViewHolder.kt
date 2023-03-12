package com.example.githubusers.ui

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.githubusers.data.GitHubUser
import com.example.githubusers.databinding.ItemGitHubUsersRecycleViewBinding

class GitHubUsersViewHolder(private val binding: ItemGitHubUsersRecycleViewBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(gitHubUser: GitHubUser){
        binding.apply {
            gitHubUsersAvatarImageView.load(gitHubUser.avatarUrl)
            gitHubUserLogin.text = gitHubUser.login
            gitHubUserId.text = gitHubUser.id.toString()
        }
    }
}