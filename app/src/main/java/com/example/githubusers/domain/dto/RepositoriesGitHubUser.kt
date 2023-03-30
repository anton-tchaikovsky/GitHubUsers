package com.example.githubusers.domain.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoriesGitHubUser(
    val id: Int,
    val name: String
):Parcelable