package com.example.githubusers.data.repository

import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.data.api.RemoteDataSourceGitHubUsers
import com.example.githubusers.data.repository.cache.RepositoriesGitHubUserCache
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.domain.repository.IRepositoriesGitHubUserRepository
import com.example.githubusers.domain.repository.cache.IRepositoriesGitHubUserCache
import com.example.githubusers.domain.repository.network.INetWorkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RepositoriesGitHubUserRepositoryImpl: IRepositoriesGitHubUserRepository {

    init {
        GitHubUsersApp.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var netWorkStatus: INetWorkStatus

    private val remoteDataSourceGitHubUsers: RemoteDataSourceGitHubUsers by lazy {
        RemoteDataSourceGitHubUsers()
    }

    private val repositoriesGitHubUserCache: IRepositoriesGitHubUserCache by lazy {
        RepositoriesGitHubUserCache()
    }

    override fun getRepositoriesGitHubUser(gitHubUser: GitHubUser): Single<List<RepositoryGitHubUser>> =
        netWorkStatus.isConnectSingle()
            .flatMap { isConnect ->
                if (isConnect){
                    remoteDataSourceGitHubUsers.callAPIRepositoriesGitHubUser(gitHubUser.reposUrl)
                        .flatMap { repositoriesGitHubUser ->
                            Single.fromCallable {
                                repositoriesGitHubUserCache.saveToCache(
                                    gitHubUser,
                                    repositoriesGitHubUser
                                )
                                    .subscribeBy(
                                        onError = {
                                            it.printStackTrace()
                                        }
                                    )
                                return@fromCallable repositoriesGitHubUser
                            }
                        }
                } else {
                    return@flatMap repositoriesGitHubUserCache.readFromCache(gitHubUser)
                }
            }
            .subscribeOn(Schedulers.io())
}