package com.example.githubusers.data

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    val id: Int,
    val login: String,
    @field:SerializedName("avatar_url")val avatarUrl: String
)