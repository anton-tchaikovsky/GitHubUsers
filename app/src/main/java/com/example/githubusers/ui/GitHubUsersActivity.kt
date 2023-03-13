package com.example.githubusers.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.ActivityGitHubUsersBinding
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.gitHubUserApp

private lateinit var binding: ActivityGitHubUsersBinding
private lateinit var gitHubUsersAdapter: GitHubUsersAdapter

class GitHubUsersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        loadGitHubUsers()
    }

    private fun initView() {
        initRecycleView()
        initRefreshButton()
    }

    private fun initRefreshButton() {
        binding.refreshButton.setOnClickListener {
            loadGitHubUsers()
        }
    }

    private fun showGitHubUsers(gitHubUsers: List<GitHubUser>) {
        gitHubUsersAdapter.updateGitHubUsersList(gitHubUsers)
    }

    private fun showError(error: Throwable) {
        Log.v("@@@", error.message.toString())
    }

    private fun loadGitHubUsers() {
        gitHubUserApp.gitHubUsersRepository.getGitHubUsers(::showGitHubUsers, ::showError)
    }

    private fun initRecycleView() {
        binding.gitHubUsersRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@GitHubUsersActivity, RecyclerView.VERTICAL, false)
            gitHubUsersAdapter = GitHubUsersAdapter().also {
                adapter = it
            }
        }
    }
}