package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.github.terrakok.cicerone.Screen

interface GitHubUsersAppScreens {
    fun avatarGitHubUserScreen (login:String, avatarUrl:String): Screen
    fun gitHubImageScreen():Screen
    fun repositoriesGitHubUserScreen(gitHubUser: GitHubUser, repositoriesGitHubUser: List<RepositoryGitHubUser>):Screen
}