package com.example.githubusers.domain.repository.git_hub_image

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

interface IGitHubImageRepository {
    fun readGitHubImage(): Maybe<Bitmap>
    fun saveGitHubImagePng(gitHubImage: Bitmap):Completable
    fun getProgressSaveGitHubImagePng(): Observable<Long>
}
