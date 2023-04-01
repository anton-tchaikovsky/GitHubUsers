package com.example.githubusers.domain.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryGitHubUser(
    val id: Int,
    val name: String,
    val language: String?,
    @field: SerializedName("stargazers_count") val stargazersCount: Int,
    @field: SerializedName("watchers_count") val watchersCount: Int,
    @field: SerializedName("forks_count") val forksCount: Int

):Parcelable