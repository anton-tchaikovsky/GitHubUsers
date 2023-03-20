package com.example.githubusers.data.repository

import com.example.githubusers.data.api.RemoteDataSourceGitHubUsers
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubUsersRepository
import com.example.githubusers.utils.DEFAULT_GIT_HAB_USERS_LIST
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

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

    override fun getDefaultGitHubUsers(): Observable<List<GitHubUser>> {
        return Observable.intervalRange(0, 3, 0, 2, TimeUnit.SECONDS)
            .flatMap {
                return@flatMap when (it) {
                    0L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .map { defaultGitHubUsersList ->
                            listOf(defaultGitHubUsersList[0])
                        }
                    1L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .map { defaultGitHubUsersList ->
                            listOf(defaultGitHubUsersList[0], defaultGitHubUsersList[1])
                        }
                    2L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                    else -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                }
            }
    }

}