package com.example.githubusers.utils

import com.example.githubusers.data.room.RoomGitHubUser
import com.example.githubusers.data.room.RoomRepositoryGitHubUser
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser

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

fun mapFromRepositoriesGitHubUserToRoomRepositoryGitHubUser (gitHubUser: GitHubUser, repositoriesGitHubUser: List<RepositoryGitHubUser>): List<RoomRepositoryGitHubUser> {
    val roomRepositoriesGitHubUser = mutableListOf<RoomRepositoryGitHubUser>()
    repositoriesGitHubUser.forEach {
        roomRepositoriesGitHubUser.add(RoomRepositoryGitHubUser(it.id, it.name, it.language, it.stargazersCount, it.watchersCount, it.forksCount, gitHubUser.id))
    }
    return roomRepositoriesGitHubUser.toList()
}

fun mapFromRoomRepositoriesGitHubUserToRepositoriesGitHubUser (roomRepositoriesGitHubUser: List<RoomRepositoryGitHubUser>): List<RepositoryGitHubUser> {
    val repositoriesGitHubUser = mutableListOf<RepositoryGitHubUser>()
    roomRepositoriesGitHubUser.forEach {
        repositoriesGitHubUser.add(RepositoryGitHubUser(it.id, it.name, it.language, it.stargazersCount, it.watchersCount, it.forksCount))
    }
    return repositoriesGitHubUser.toList()
}