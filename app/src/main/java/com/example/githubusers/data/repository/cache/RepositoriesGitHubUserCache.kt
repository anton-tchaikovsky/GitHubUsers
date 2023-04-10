package com.example.githubusers.data.repository.cache

import com.example.githubusers.data.room.DatabaseGitHubUsers
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.domain.repository.cache.IRepositoriesGitHubUserCache
import com.example.githubusers.utils.mapFromRepositoriesGitHubUserToRoomRepositoryGitHubUser
import com.example.githubusers.utils.mapFromRoomRepositoriesGitHubUserToRepositoriesGitHubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.io.IOException

class RepositoriesGitHubUserCache (private val databaseGitHubUsers: DatabaseGitHubUsers): IRepositoriesGitHubUserCache {

    override fun saveToCache(
        gitHubUser: GitHubUser,
        repositoriesGitHubUser: List<RepositoryGitHubUser>
    ): Completable =
        Completable.create {
// Если в БД пользователь не сохранен, то список репозиториев, относящихся к этому пользователю
//  также не сохраняется: испускается ошибка.
            if (databaseGitHubUsers.gitHubUserDao().findByLogin(gitHubUser.login) != null) {
                databaseGitHubUsers.repositoryGitHubUserDao().insert(
                    mapFromRepositoriesGitHubUserToRoomRepositoryGitHubUser(
                        gitHubUser,
                        repositoriesGitHubUser
                    )
                )
                it.onComplete()
            } else
                it.onError(IOException(MESSAGE_ERROR_GIT_HUB_USR_NOT_FOUND))
        }

    override fun readFromCache(gitHubUser: GitHubUser): Single<List<RepositoryGitHubUser>> =
        Single.fromCallable{
            mapFromRoomRepositoriesGitHubUserToRepositoriesGitHubUser(databaseGitHubUsers.repositoryGitHubUserDao().findByUserId(gitHubUser.id))
        }

    companion object{
        const val MESSAGE_ERROR_GIT_HUB_USR_NOT_FOUND = "Github user was not found in the database"
    }

}