package com.example.githubusers.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubUser(
    @PrimaryKey val id: Int,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String
)