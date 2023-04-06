package com.example.githubusers.data.room.dao

import androidx.room.*
import com.example.githubusers.data.room.entity.RoomGitHubUser
import com.example.githubusers.data.room.entity.RoomRepositoryGitHubUser

@Dao
interface GitHubUserDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(gitHubUser: RoomRepositoryGitHubUser)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg gitHubUsers: RoomRepositoryGitHubUser)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert (gitHubUsers: List<RoomGitHubUser>)

    @Delete
    fun delete(gitHubUser: RoomGitHubUser)

    @Delete
    fun delete (vararg gitHubUsers: RoomRepositoryGitHubUser)

    @Delete
    fun delete(gitHubUser: List<RoomGitHubUser>)

    @Query ("DELETE FROM RoomGitHubUser WHERE login = :login")
    fun deleteByLogin(login: String)

    @Query("SELECT * FROM RoomGitHubUser")
    fun getAll(): List <RoomGitHubUser>

    @Query("SELECT * FROM RoomGithubUser WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGitHubUser?

    @Update
    fun update (gitHubUser: RoomRepositoryGitHubUser)

    @Update
    fun update (vararg gitHubUser: RoomRepositoryGitHubUser)

    @Update
    fun update (gitHubUser: List<RoomRepositoryGitHubUser>)

}