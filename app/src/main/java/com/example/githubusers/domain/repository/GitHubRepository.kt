package com.example.githubusers.domain.repository

import com.example.githubusers.domain.dto.GitHubUser
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import java.io.File

interface GitHubRepository {
    fun getGitHubUsers(): Single<List<GitHubUser>>
    fun getDefaultGitHubUsers(): Observable<List<GitHubUser>>
    fun loadGitHubImage(): Single<ResponseBody>
    fun readGitHubImage(): Maybe<File>
    fun saveGitHubImage(responseBodyGitHubImage: ResponseBody)
}
