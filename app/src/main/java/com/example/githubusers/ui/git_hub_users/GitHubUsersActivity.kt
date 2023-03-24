package com.example.githubusers.ui.git_hub_users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.example.githubusers.R
import com.example.githubusers.databinding.ActivityGitHubUsersBinding
import com.example.githubusers.gitHubUserApp
import com.example.githubusers.utils.DURATION_FADE_IN_GIT_HUB_USERS
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class GitHubUsersActivity : MvpAppCompatActivity(), GitHubUsersView {

    private lateinit var binding: ActivityGitHubUsersBinding
    private val gitHubUsersPresenter by moxyPresenter { GitHubUsersPresenter(gitHubUserApp.gitHubRepository, gitHubUserApp.router, GitHubUsersAppScreensImpl()) }
    private val gitHubUsersAdapter by lazy { GitHubUsersAdapter(gitHubUsersPresenter.itemGitHubUsersPresenter) }
    private val navigator:Navigator = AppNavigator(this, R.id.git_hub_users_container)

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        gitHubUserApp.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        gitHubUserApp.navigatorHolder.removeNavigator()
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
        val transition = Fade(Fade.IN).apply {
            duration = DURATION_FADE_IN_GIT_HUB_USERS
        }
        TransitionManager.beginDelayedTransition(binding.gitHubUsersContainer, transition)
    }

    override fun initView() {
        initLoadingIndicator()
        initRecycleView()
        initRefreshButton()
        initDefaultUsersButton()
    }

    private fun initLoadingIndicator() {
        hideLoading()
    }

    private fun initRefreshButton() {
        binding.refreshButton.setOnClickListener {
            gitHubUsersPresenter.onRequestGitHubUsers()
        }
    }

    private fun initDefaultUsersButton() {
        binding.defaultUsersButton.setOnClickListener {
            gitHubUsersPresenter.onRequestDefaultGitHubUsers()
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