package com.example.githubusers.ui.git_hub_users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.ItemGitHubUsersRecycleViewBinding

class GitHubUsersAdapter(
    private val itemGitHubUsersPresenter: ItemGitHubUsersPresenter,
    private val imageLoader: ImageLoader<AppCompatImageView>
) : RecyclerView.Adapter<ItemGitHubUsersViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemGitHubUsersViewHolder {
        val binding = ItemGitHubUsersRecycleViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemGitHubUsersViewHolder(binding, imageLoader)
    }

    override fun onBindViewHolder(holder: ItemGitHubUsersViewHolder, position: Int) {
        holder.itemPosition = position
        itemGitHubUsersPresenter.bindView(holder)
    }

    override fun getItemCount(): Int = itemGitHubUsersPresenter.getCount()

    override fun getItemId(position: Int) = itemGitHubUsersPresenter.getId(position).toLong()

}