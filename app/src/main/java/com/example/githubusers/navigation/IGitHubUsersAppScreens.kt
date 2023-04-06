package com.example.githubusers.navigation

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.github.terrakok.cicerone.Screen

interface IGitHubUsersAppScreens {
    fun avatarGitHubUserScreen (login:String, avatarUrl:String): Screen
    fun gitHubImageScreen():Screen
    fun repositoriesGitHubUserScreen(
        gitHubUser: GitHubUser,
        repositoriesGitHubUser: List<RepositoryGitHubUser>
    ): Screen
    fun infoAboutRepositoryGitHubUserScreen(repositoryGitHubUser: RepositoryGitHubUser): Screen
}