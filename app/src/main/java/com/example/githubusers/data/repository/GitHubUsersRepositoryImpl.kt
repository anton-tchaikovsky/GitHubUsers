package com.example.githubusers.data.repository

import com.example.githubusers.data.api.RemoteDataSourceGitHubUsers
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubUsersRepository
import com.example.githubusers.utils.DEFAULT_GIT_HAB_USERS_LIST
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class GitHubUsersRepositoryImpl:GitHubUsersRepository {

    override fun getGitHubUsers():  Observable<List<GitHubUser>>{
        return Observable.fromCallable {
            RemoteDataSourceGitHubUsers().callAPIGitHubUsers().body()
        }
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