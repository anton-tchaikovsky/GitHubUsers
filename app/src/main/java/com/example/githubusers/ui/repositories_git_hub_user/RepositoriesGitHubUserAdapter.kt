package com.example.githubusers.ui.repositories_git_hub_user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.ItemRepositoriesRecycleViewBinding

class RepositoriesGitHubUserAdapter(private val itemGitHubUsersPresenter: ItemRepositoriesGitHubUserPresenter): RecyclerView.Adapter<ItemRepositoriesGitHubUserViewHolder>(){

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRepositoriesGitHubUserViewHolder {
        val binding = ItemRepositoriesRecycleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return ItemRepositoriesGitHubUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemRepositoriesGitHubUserViewHolder, position: Int) {
        holder.itemPosition = position
        itemGitHubUsersPresenter.bindView(holder)
    }

    override fun getItemCount(): Int = itemGitHubUsersPresenter.getCount()

    override fun getItemId(position: Int) = itemGitHubUsersPresenter.getId(position).toLong()

}