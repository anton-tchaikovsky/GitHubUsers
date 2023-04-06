package com.example.githubusers.data.api

import com.example.githubusers.utils.BASE_URL_API_GIT_HUB_IMAGE
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSourceGitHubImage {

    private val retrofitGitHubImage: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_API_GIT_HUB_IMAGE)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val apiGitHubImage = retrofitGitHubImage.create(IApiGitHubImage::class.java)

    fun callAPIGitHubImage(): Single<ResponseBody> =
        apiGitHubImage.loadGitHubImage()

}