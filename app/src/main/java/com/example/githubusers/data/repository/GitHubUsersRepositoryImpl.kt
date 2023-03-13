package com.example.githubusers.data.repository

import com.example.githubusers.data.api.RemoteDataSourceGitHubUsers
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubUsersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubUsersRepositoryImpl:GitHubUsersRepository {

    override fun getGitHubUsers(
        onSuccess: (List<GitHubUser>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        RemoteDataSourceGitHubUsers().callAPIGitHubUsers(object : Callback<List<GitHubUser>> {
            override fun onResponse(
                call: Call<List<GitHubUser>>,
                response: Response<List<GitHubUser>>
            ) {
                if (response.isSuccessful && response.body() != null)
                    onSuccess(response.body() as List<GitHubUser>)
                else
                    onError?.invoke(IllegalStateException())
            }
            override fun onFailure(call: Call<List<GitHubUser>>, t: Throwable) {
                onError?.invoke(t)
            }
        })
    }
}