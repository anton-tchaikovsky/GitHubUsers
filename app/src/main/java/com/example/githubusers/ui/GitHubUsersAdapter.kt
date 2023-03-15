package com.example.githubusers.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.ItemGitHubUsersRecycleViewBinding

class GitHubUsersAdapter(private val itemGitHubUsersPresenter: ItemGitHubUsersPresenter): RecyclerView.Adapter<GitHubUsersViewHolder>(){

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUsersViewHolder {
        val binding = ItemGitHubUsersRecycleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return GitHubUsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitHubUsersViewHolder, position: Int) {
        itemGitHubUsersPresenter.bindView(holder, position)
    }

    override fun getItemCount(): Int = itemGitHubUsersPresenter.getCount()

    override fun getItemId(position: Int) = itemGitHubUsersPresenter.getId(position).toLong()

}