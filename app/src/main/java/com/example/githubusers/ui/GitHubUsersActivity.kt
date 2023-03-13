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

@Suppress("DEPRECATION")
class GitHubUsersActivity : AppCompatActivity(), GitHubUsersContract.GitHubUsersView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        gitHubUsersPresenter = extractGitHubUsersPresenter()
            .also {
                it.attach(this)
                // it.onRequestGitHubUsers()
            }
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): GitHubUsersContract.GitHubUsersPresenter {
        return gitHubUsersPresenter
    }

    override fun onDestroy() {
        gitHubUsersPresenter.detach()
        super.onDestroy()
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

    private fun extractGitHubUsersPresenter(): GitHubUsersContract.GitHubUsersPresenter =
        lastCustomNonConfigurationInstance as? GitHubUsersContract.GitHubUsersPresenter
            ?: GitHubUsersPresenter(gitHubUserApp.gitHubUsersRepository)

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