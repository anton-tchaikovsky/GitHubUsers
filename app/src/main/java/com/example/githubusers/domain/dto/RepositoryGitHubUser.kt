package com.example.githubusers.domain.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryGitHubUser(
    val id: Int,
    val name: String
):Parcelable