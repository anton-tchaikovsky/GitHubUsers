package com.example.githubusers.domain.repository.git_hub_image

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

interface IGitHubImageLoaderRepository {
    fun loadGitHubImage(): Single<ResponseBody>
    fun saveGitHubImageJpg(responseBodyGitHubImage: ResponseBody): Completable
}