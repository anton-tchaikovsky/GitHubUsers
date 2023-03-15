package com.example.githubusers.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.ItemGitHubUsersRecycleViewBinding

class GitHubUsersAdapter(private val itemGitHubUsersPresenter: ItemGitHubUsersPresenter): RecyclerView.Adapter<ItemGitHubUsersViewHolder>(){

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemGitHubUsersViewHolder {
        val binding = ItemGitHubUsersRecycleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return ItemGitHubUsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemGitHubUsersViewHolder, position: Int) {
        holder.itemPosition = position
        itemGitHubUsersPresenter.bindView(holder)
    }

    override fun getItemCount(): Int = itemGitHubUsersPresenter.getCount()

    override fun getItemId(position: Int) = itemGitHubUsersPresenter.getId(position).toLong()

}