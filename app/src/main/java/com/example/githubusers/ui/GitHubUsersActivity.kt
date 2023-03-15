package com.example.githubusers.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.example.githubusers.databinding.ActivityGitHubUsersBinding
import com.example.githubusers.gitHubUserApp
import com.example.githubusers.utils.DURATION_FADE_IN_GIT_HUB_USERS
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class GitHubUsersActivity : MvpAppCompatActivity(), GitHubUsersView {

    private lateinit var binding: ActivityGitHubUsersBinding
    private val gitHubUsersPresenter by moxyPresenter { GitHubUsersPresenter(gitHubUserApp.gitHubUsersRepository) }
    private val gitHubUsersAdapter by lazy { GitHubUsersAdapter(gitHubUsersPresenter.itemGitHubUsersPresenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showGitHubUsers() {
        gitHubUsersAdapter.notifyDataSetChanged()
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.gitHubUsersRecyclerView.visibility = View.GONE
        binding.loadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.gitHubUsersRecyclerView.visibility = View.VISIBLE
        binding.loadingIndicator.visibility = View.GONE
    }

    override fun showAnimateGitHubUsers() {
        Log.v("@@@", binding.gitHubUsersRecyclerView.isVisible.toString())
        val transition = Fade(Fade.IN).apply {
            duration = DURATION_FADE_IN_GIT_HUB_USERS
        }
        TransitionManager.beginDelayedTransition(binding.gitHubUsersContainer, transition)
    }

    override fun initView() {
        initLoadingIndicator()
        initRecycleView()
        initRefreshButton()
    }

    private fun initLoadingIndicator() {
        hideLoading()
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
            adapter = gitHubUsersAdapter
        }
    }

}