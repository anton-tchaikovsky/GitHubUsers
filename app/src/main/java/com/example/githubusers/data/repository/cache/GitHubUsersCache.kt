package com.example.githubusers.data.repository.cache

import com.example.githubusers.data.room.DatabaseGitHubUsers
import com.example.githubusers.domain.repository.cache.IGitHubUsersCache
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.utils.mapFromGitHubUsersToRoomGitHubUsers
import com.example.githubusers.utils.mapFromRoomGitHubUsersToGitHubUsers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class GitHubUsersCache: IGitHubUsersCache {
    private val databaseGitHubUsers: DatabaseGitHubUsers
        get() = DatabaseGitHubUsers.getInstanceDatabase()

    override fun saveToCache(gitHubUsers: List<GitHubUser>): Completable =
        Completable.fromAction {
            databaseGitHubUsers.gitHubUserDao().insert(
                mapFromGitHubUsersToRoomGitHubUsers(gitHubUsers))
        }

    override fun readFromCache(): Single<List<GitHubUser>> =
        Single.fromCallable {
            mapFromRoomGitHubUsersToGitHubUsers(databaseGitHubUsers.gitHubUserDao().getAll())
        }
}