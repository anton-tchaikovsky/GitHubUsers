package com.example.githubusers.data.repository

import android.os.Environment
import com.example.githubusers.data.api.RemoteDataSourceGitHubImage
import com.example.githubusers.data.api.RemoteDataSourceGitHubUsers
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubRepository
import com.example.githubusers.utils.DEFAULT_GIT_HAB_USERS_LIST
import com.example.githubusers.utils.GIT_HUB_IMAGE
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class GitHubRepositoryImpl:GitHubRepository {

    override fun getGitHubUsers(): Single<List<GitHubUser>> =
        RemoteDataSourceGitHubUsers().callAPIGitHubUsers()

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

    override fun getGitHubImage(): Single<ResponseBody> =
        RemoteDataSourceGitHubImage().callAPIGitHubImage()

    override fun saveGitHubImage(responseBodyGitHubImage: ResponseBody) {
        val path: File = Environment.getExternalStorageDirectory()
        val file = File(path, GIT_HUB_IMAGE)
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(responseBodyGitHubImage.bytes())
    }

}