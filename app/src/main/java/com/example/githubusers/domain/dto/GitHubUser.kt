package com.example.githubusers.domain.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubUser(
    val id: Int,
    val login: String,
    @field:SerializedName("avatar_url")val avatarUrl: String,
    @field:SerializedName("repos_url")val reposUrl: String
): Parcelable