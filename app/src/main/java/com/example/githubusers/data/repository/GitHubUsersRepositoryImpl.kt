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

    override fun getGitHubUsers():  Observable<List<GitHubUser>> {
        return Observable.create { emitter ->
            RemoteDataSourceGitHubUsers().callAPIGitHubUsers(object : Callback<List<GitHubUser>> {
                override fun onResponse(
                    call: Call<List<GitHubUser>>,
                    response: Response<List<GitHubUser>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        emitter.run {
                            onNext(response.body() as List<GitHubUser>)
                            onComplete()
                        }
                    } else
                        emitter.onError(IllegalStateException())
                }

                override fun onFailure(call: Call<List<GitHubUser>>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    override fun getDefaultGitHubUsers(): Observable<List<GitHubUser>> {
        return Observable.intervalRange(0, 5, 0, 10, TimeUnit.MILLISECONDS)
            .switchMap {
                return@switchMap when (it) {
                    0L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .map { defaultGitHubUsersList ->
                            listOf(defaultGitHubUsersList[0])
                        }
                        .delay(6L, TimeUnit.SECONDS)
                    1L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .map { defaultGitHubUsersList ->
                            listOf(defaultGitHubUsersList[0], defaultGitHubUsersList[1])
                        }
                        .delay(5L, TimeUnit.SECONDS)
                    2L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .map { defaultGitHubUsersList ->
                            listOf(
                                defaultGitHubUsersList[0],
                                defaultGitHubUsersList[1],
                                defaultGitHubUsersList[2]
                            )
                        }
                        .delay(4L, TimeUnit.SECONDS)
                    3L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .map { defaultGitHubUsersList ->
                            listOf(
                                defaultGitHubUsersList[0],
                                defaultGitHubUsersList[1],
                                defaultGitHubUsersList[2],
                                defaultGitHubUsersList[3]
                            )
                        }
                        .delay(3L, TimeUnit.SECONDS)
                    4L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .delay(2L, TimeUnit.SECONDS)
                    else -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                }
            }
    }

}