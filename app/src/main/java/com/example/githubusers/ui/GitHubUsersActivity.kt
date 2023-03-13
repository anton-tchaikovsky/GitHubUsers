package com.example.githubusers.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.ActivityGitHubUsersBinding
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.gitHubUserApp

private lateinit var binding: ActivityGitHubUsersBinding
private lateinit var gitHubUsersAdapter:GitHubUsersAdapter
private lateinit var gitHubUsersPresenter: GitHubUsersContract.GitHubUsersPresenter

class GitHubUsersActivity : AppCompatActivity(), GitHubUsersContract.GitHubUsersView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        gitHubUsersPresenter = GitHubUsersPresenter(gitHubUserApp.gitHubUsersRepository).also {
            it.attach(this)
                // it.onRequestGitHubUsers()
        }
    }

    private fun initView() {
        initLoadingIndicator()
        initRecycleView()
        initRefreshButton()
    }

    private fun initLoadingIndicator() {
       showLoading(false)
    }

    private fun initRefreshButton() {
        binding.refreshButton.setOnClickListener {
            gitHubUsersPresenter.onRequestGitHubUsers()
        }
    }

    override fun showGitHubUsers(gitHubUsers: List<GitHubUser>) {
        gitHubUsersAdapter.updateGitHubUsersList(gitHubUsers)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showLoading(isShowLoading: Boolean) {
        if (isShowLoading) {
            binding.gitHubUsersRecyclerView.visibility = View.GONE
            binding.loadingIndicator.visibility = View.VISIBLE
        } else {
            binding.gitHubUsersRecyclerView.visibility = View.VISIBLE
            binding.loadingIndicator.visibility = View.GONE
        }
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

    override fun onDestroy() {
        gitHubUsersPresenter.detach()
        super.onDestroy()
    }
}