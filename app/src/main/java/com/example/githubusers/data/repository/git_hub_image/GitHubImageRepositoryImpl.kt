package com.example.githubusers.data.repository.git_hub_image

import android.graphics.Bitmap
import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.domain.repository.git_hub_image.IGitHubImageRepository
import com.example.githubusers.domain.repository.storage_directory.IImageStorageDirectory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GitHubImageRepositoryImpl:
    IGitHubImageRepository {

    init {
        GitHubUsersApp.instance.gitHubImageSubcomponent?.inject(this)
    }

    @Inject
    lateinit var imageStorageDirectory: IImageStorageDirectory

    override fun saveGitHubImagePng(gitHubImage: Bitmap): Completable =
        imageStorageDirectory.saveGitHubImagePng(gitHubImage)

    override fun readGitHubImage(): Maybe<Bitmap> =
        imageStorageDirectory.readGitHubImage()

    override fun getProgressSaveGitHubImagePng(): Observable<Long> {
        return Observable.intervalRange(0, COUNT_INTERVAL_RANGE, 0, PERIOD_INTERVAL_RANGE, TimeUnit.MILLISECONDS)
    }

    companion object{
        private const val COUNT_INTERVAL_RANGE = 5001L
        private const val PERIOD_INTERVAL_RANGE = 1L
    }

}