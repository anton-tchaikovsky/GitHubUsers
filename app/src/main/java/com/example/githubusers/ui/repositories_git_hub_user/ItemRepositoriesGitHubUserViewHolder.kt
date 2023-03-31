package com.example.githubusers.ui.repositories_git_hub_user

import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.ItemRepositoriesRecycleViewBinding

class ItemRepositoriesGitHubUserViewHolder
    (private val binding: ItemRepositoriesRecycleViewBinding):
    RecyclerView.ViewHolder(binding.root), ItemRepositoriesGitHubUserView {

    override var itemPosition: Int? = null

    override fun showName(name: String) {
        binding.repositoryName.text = name
    }

    override fun showId(id: Int) {
        binding.repositoryId.text = id.toString()
    }

    override fun setClickListener(itemClickListener: (ItemRepositoriesGitHubUserView) -> Unit) {
        itemView.setOnClickListener{
            itemClickListener(this@ItemRepositoriesGitHubUserViewHolder)
        }
    }

}