package com.example.githubusers.utils

import com.example.githubusers.data.room.RoomGitHubUser
import com.example.githubusers.domain.dto.GitHubUser

fun mapFromGitHubUsersToRoomGitHubUsers (gitHubUsers: List<GitHubUser>): List<RoomGitHubUser> {
    val roomGitHubUsers = mutableListOf<RoomGitHubUser>()
    gitHubUsers.forEach {
        roomGitHubUsers.add(RoomGitHubUser(it.id, it.login, it.avatarUrl, it.reposUrl))
    }
    return roomGitHubUsers.toList()
}

fun mapFromRoomGitHubUsersToGitHubUsers (roomGitHubUsers: List<RoomGitHubUser>): List<GitHubUser> {
    val gitHubUsers = mutableListOf<GitHubUser>()
    roomGitHubUsers.forEach {
        gitHubUsers.add(GitHubUser(it.id, it.login, it.avatarUrl, it.reposUrl))
    }
    return gitHubUsers.toList()
}
