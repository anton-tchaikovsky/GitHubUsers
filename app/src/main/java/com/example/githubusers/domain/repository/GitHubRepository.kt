package com.example.githubusers.domain.repository

import android.graphics.Bitmap
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

interface GitHubRepository {
    fun getGitHubUsers(): Single<List<GitHubUser>>
    fun getRepositoriesGitHubUser(gitHubUser: GitHubUser): Single<List<RepositoryGitHubUser>>
    fun getDefaultGitHubUsers(): Observable<List<GitHubUser>>
    fun loadGitHubImage(): Single<ResponseBody>
    fun readGitHubImage(): Maybe<Bitmap>
    fun saveGitHubImageJpg(responseBodyGitHubImage: ResponseBody):Completable
    fun saveGitHubImagePng(gitHubImage: Bitmap):Completable
    fun getProgressSaveGitHubImagePng(): Observable<Long>
}
