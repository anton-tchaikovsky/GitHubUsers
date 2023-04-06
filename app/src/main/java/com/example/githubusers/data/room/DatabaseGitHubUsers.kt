package com.example.githubusers.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        private var instanceDatabase: DatabaseGitHubUsers? = null
        private const val DATABASE_NAME = "GitHubUser.db"
        private const val DATABASE_HAS_NOT_CREATED = "Database has not been created."

        fun getInstanceDatabase(): DatabaseGitHubUsers =
            instanceDatabase ?: throw RuntimeException(DATABASE_HAS_NOT_CREATED)

        fun createInstanceDatabase(context: Context) {
            if (instanceDatabase == null) {
                instanceDatabase = Room.databaseBuilder(
                    context,
                    DatabaseGitHubUsers::class.java,
                    DATABASE_NAME
                ).build()
            }
        }
    }
}