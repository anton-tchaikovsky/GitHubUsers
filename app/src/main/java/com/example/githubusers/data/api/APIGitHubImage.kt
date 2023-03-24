package com.example.githubusers.data.api

import com.example.githubusers.utils.END_POINT_API_GIT_HUB_IMAGE
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface APIGitHubImage {

    @GET(END_POINT_API_GIT_HUB_IMAGE)
    fun loadGitHubImage(): Single<ResponseBody>
}