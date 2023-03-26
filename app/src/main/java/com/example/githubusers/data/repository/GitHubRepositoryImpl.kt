package com.example.githubusers.data.repository

import android.os.Environment
import com.example.githubusers.data.api.RemoteDataSourceGitHubImage
import com.example.githubusers.data.api.RemoteDataSourceGitHubUsers
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubRepository
import com.example.githubusers.utils.DEFAULT_GIT_HAB_USERS_LIST
import com.example.githubusers.utils.GIT_HUB_IMAGE
import com.example.githubusers.utils.MESSAGE_ERROR_FOR_PERMISSION_TO_READ
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit

class GitHubRepositoryImpl:GitHubRepository {

    private val path: File = Environment.getExternalStorageDirectory()
    private val file = File(path, GIT_HUB_IMAGE)

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

    override fun loadGitHubImage(): Single<ResponseBody> =
        RemoteDataSourceGitHubImage().callAPIGitHubImage()

    override fun saveGitHubImage(responseBodyGitHubImage: ResponseBody) {
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(responseBodyGitHubImage.bytes())
    }

    override fun readGitHubImage():Maybe<File>{
        return Maybe.create {
            if (!file.exists())
                it.onComplete()
            else if(file.canRead())
                it.onSuccess(file)
            else
                it.onError(IOException(MESSAGE_ERROR_FOR_PERMISSION_TO_READ))
        }
    }

}