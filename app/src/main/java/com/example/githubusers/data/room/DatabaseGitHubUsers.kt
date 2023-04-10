package com.example.githubusers.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubusers.data.room.dao.GitHubUserDao
import com.example.githubusers.data.room.dao.RepositoryGitHubUserDao
import com.example.githubusers.data.room.entity.RoomGitHubUser
import com.example.githubusers.data.room.entity.RoomRepositoryGitHubUser

@Database(
    entities = [RoomGitHubUser::class, RoomRepositoryGitHubUser::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseGitHubUsers : RoomDatabase() {

    abstract fun gitHubUserDao(): GitHubUserDao

    abstract fun repositoryGitHubUserDao(): RepositoryGitHubUserDao

}