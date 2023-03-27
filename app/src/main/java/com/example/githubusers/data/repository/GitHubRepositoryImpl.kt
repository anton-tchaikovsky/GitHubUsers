package com.example.githubusers.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.githubusers.data.api.RemoteDataSourceGitHubImage
import com.example.githubusers.data.api.RemoteDataSourceGitHubUsers
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubRepository
import com.example.githubusers.utils.*
import io.reactivex.rxjava3.core.Completable
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
    private val fileJpg = File(path, GIT_HUB_IMAGE_JPG)
    private val filePng = File(path, GIT_HUB_IMAGE_PNG)
    private val qualityCompressToPng = 100

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

    override fun saveGitHubImageJpg(responseBodyGitHubImage: ResponseBody): Completable {
        return Completable.fromAction {
            var fileOutputStream: FileOutputStream? = null
            try {
                fileOutputStream = FileOutputStream(fileJpg)
                fileOutputStream.write(responseBodyGitHubImage.bytes())
            } catch (e: Exception) {
                throw e
            } finally {
                try {
                    fileOutputStream?.close()
                } catch (e: IOException) {
                    throw e
                }
            }
        }
    }

    override fun saveGitHubImagePng(gitHubImage: Bitmap): Completable {
        return Completable.create {
            var fileOutputStream: FileOutputStream? = null
            try {
                fileOutputStream = FileOutputStream(filePng)
                if (gitHubImage.compress(
                        Bitmap.CompressFormat.PNG,
                        qualityCompressToPng,
                        fileOutputStream
                    )
                )
                    it.onComplete()
                else
                    it.onError(IllegalArgumentException(MESSAGE_ERROR_CONVERSION_TO_PNG))
            } catch (e: Exception) {
                throw e
            } finally {
                try {
                    fileOutputStream?.close()
                } catch (e: IOException) {
                    throw e
                }
            }
        }
    }

    override fun readGitHubImage(): Maybe<Bitmap> {
        return Maybe.create {
            if (!fileJpg.exists())
                it.onComplete()
            else if (fileJpg.canRead()) {
                val bitmapGitHubImage = BitmapFactory.decodeFile(fileJpg.absolutePath)
                if (bitmapGitHubImage != null)
                    it.onSuccess(bitmapGitHubImage)
                else
                    it.onError(IOException(MESSAGE_ERROR_READ_JPG))
            } else
                it.onError(IOException(MESSAGE_ERROR_PERMISSION_TO_READ))
        }
    }

}