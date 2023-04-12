package com.example.githubusers.domain.repository.storage_directory

import io.reactivex.rxjava3.core.Completable
import okhttp3.ResponseBody

interface IImageLoaderStorageDirectory {
    fun saveGitHubImageJpg(responseBodyGitHubImage: ResponseBody): Completable
}