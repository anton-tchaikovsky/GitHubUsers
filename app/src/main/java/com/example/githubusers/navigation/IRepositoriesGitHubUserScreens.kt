package com.example.githubusers.navigation

import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.github.terrakok.cicerone.Screen

interface IRepositoriesGitHubUserScreens {
    fun infoAboutRepositoryGitHubUserScreen(repositoryGitHubUser: RepositoryGitHubUser): Screen
}