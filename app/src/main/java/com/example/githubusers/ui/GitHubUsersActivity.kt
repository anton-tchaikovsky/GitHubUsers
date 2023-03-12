package com.example.githubusers.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.data.GitHubUser
import com.example.githubusers.databinding.ActivityGitHubUsersBinding

private lateinit var binding:ActivityGitHubUsersBinding

class GitHubUsersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        initRecycleView(listOf<GitHubUser>(
            GitHubUser(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
            GitHubUser(2, "defunkt", "https://avatars.githubusercontent.com/u/2?v=4"),
            GitHubUser(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4")
        ))
    }

    private fun initRecycleView(gitHubUsersList: List<GitHubUser>) {
        binding.gitHubUsersRecyclerView.apply {
            layoutManager = LinearLayoutManager( this@GitHubUsersActivity, RecyclerView.VERTICAL, false)
            adapter = GitHubUsersAdapter(gitHubUsersList)
        }
    }
}