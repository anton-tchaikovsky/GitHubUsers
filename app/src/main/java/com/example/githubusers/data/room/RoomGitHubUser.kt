package com.example.githubusers.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubUser(
    @PrimaryKey val id: Int,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String
)