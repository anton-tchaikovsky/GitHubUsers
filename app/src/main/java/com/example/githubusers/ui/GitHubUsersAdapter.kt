package com.example.githubusers.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.databinding.ItemGitHubUsersRecycleViewBinding

class GitHubUsersAdapter: RecyclerView.Adapter<GitHubUsersViewHolder>(){

    private var gitHubUsersList:List<GitHubUser> = listOf()

    init {
        setHasStableIds(true)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateGitHubUsersList(newGitHubUsersList:List<GitHubUser>){
       gitHubUsersList = newGitHubUsersList
       notifyDataSetChanged()
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUsersViewHolder {
        val binding = ItemGitHubUsersRecycleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return GitHubUsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitHubUsersViewHolder, position: Int) {
        holder.bind(gitHubUsersList[position])
    }

    override fun getItemCount(): Int = gitHubUsersList.size

    override fun getItemId(position: Int): Long {
        return gitHubUsersList[position].id.toLong()
    }

}