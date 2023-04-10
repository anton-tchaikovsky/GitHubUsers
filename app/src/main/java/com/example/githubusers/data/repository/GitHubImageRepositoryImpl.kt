package com.example.githubusers.data.repository

import android.graphics.Bitmap
import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.data.api.RemoteDataSourceGitHubImage
import com.example.githubusers.domain.repository.IGitHubImageRepository
import com.example.githubusers.domain.repository.storage_directory.IImageStorageDirectory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GitHubImageRepositoryImpl:
    IGitHubImageRepository {

    init {
        GitHubUsersApp.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var imageStorageDirectory: IImageStorageDirectory

    @Inject
    lateinit var remoteDataSourceGitHubImage: RemoteDataSourceGitHubImage

    override fun loadGitHubImage(): Single<ResponseBody> =
        remoteDataSourceGitHubImage.callAPIGitHubImage()

    override fun saveGitHubImageJpg(responseBodyGitHubImage: ResponseBody): Completable =
        imageStorageDirectory.saveGitHubImageJpg(responseBodyGitHubImage)

    override fun saveGitHubImagePng(gitHubImage: Bitmap): Completable =
        imageStorageDirectory.saveGitHubImagePng(gitHubImage)

    override fun readGitHubImage(): Maybe<Bitmap> =
        imageStorageDirectory.readGitHubImage()

    override fun getProgressSaveGitHubImagePng(): Observable<Long> {
        return Observable.intervalRange(0, 5001, 0, 1, TimeUnit.MILLISECONDS)
    }

}