package com.example.githubusers.data.room.dao

import androidx.room.*
import com.example.githubusers.data.room.entity.RoomRepositoryGitHubUser

@Dao
interface RepositoryGitHubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositoryGitHubUser: RoomRepositoryGitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositoryGitHubUser: RoomRepositoryGitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (repositoryGitHubUser: List<RoomRepositoryGitHubUser>)

    @Delete
    fun delete(repositoryGitHubUser: RoomRepositoryGitHubUser)

    @Delete
    fun delete (vararg repositoryGitHubUser: RoomRepositoryGitHubUser)

    @Delete
    fun delete(repositoryGitHubUser: List<RoomRepositoryGitHubUser>)

    @Query("DELETE FROM RoomRepositoryGitHubUser WHERE userId = :userId")
    fun deleteByUserId(userId: Int)

    @Query("SELECT * FROM RoomRepositoryGitHubUser")
    fun getAll(): List <RoomRepositoryGitHubUser>

    @Query("SELECT * FROM RoomRepositoryGitHubUser WHERE userId = :userId")
    fun findByUserId(userId: Int): List<RoomRepositoryGitHubUser>

    @Update
    fun update (repositoryGitHubUser: RoomRepositoryGitHubUser)

    @Update
    fun update (vararg repositoryGitHubUser: RoomRepositoryGitHubUser)

    @Update
    fun update (repositoryGitHubUser: List<RoomRepositoryGitHubUser>)
}