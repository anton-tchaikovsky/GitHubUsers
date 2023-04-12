package com.example.githubusers.domain.repository.storage_directory

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface IImageStorageDirectory {
     fun saveGitHubImagePng(gitHubImage: Bitmap): Completable
     fun readGitHubImage(): Maybe<Bitmap>
}