package com.example.githubusers.data.api

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

class RemoteDataSourceGitHubImage (private val apiGitHubImage: IApiGitHubImage) {

    fun callAPIGitHubImage(): Single<ResponseBody> =
        apiGitHubImage.loadGitHubImage()

}