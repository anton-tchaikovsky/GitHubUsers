package com.example.githubusers.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.data.api.RemoteDataSourceGitHubUsers
import com.example.githubusers.databinding.ActivityGitHubUsersBinding
import com.example.githubusers.domain.dto.GitHubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private fun loadGitHubUsers() {
        RemoteDataSourceGitHubUsers().callAPIGitHubUsers(object : Callback<List<GitHubUser>> {
            override fun onResponse(
                call: Call<List<GitHubUser>>,
                response: Response<List<GitHubUser>>
            ) {
                if (response.isSuccessful && response.body() != null)
                    gitHubUsersAdapter.updateGitHubUsersList(response.body() as List<GitHubUser>)
                else
                    Log.v("@@@", "error")
            }

            override fun onFailure(call: Call<List<GitHubUser>>, t: Throwable) {
                Log.v("@@@", t.message.toString())
            }

        })
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