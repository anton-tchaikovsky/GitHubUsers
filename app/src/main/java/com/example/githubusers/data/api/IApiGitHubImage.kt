package com.example.githubusers.data.api

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface IApiGitHubImage {
    @GET(END_POINT_API_GIT_HUB_IMAGE)
    fun loadGitHubImage(): Single<ResponseBody>

    companion object{
        private const val END_POINT_API_GIT_HUB_IMAGE = "20190523/juu/kisspng-github-software-repository-computer-icons-email-5ce6e863973725.5475298415586366436194.jpg"
    }
}