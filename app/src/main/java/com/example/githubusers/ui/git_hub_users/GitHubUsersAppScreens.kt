package com.example.githubusers.ui.git_hub_users

import com.github.terrakok.cicerone.Screen

interface GitHubUsersAppScreens {
    fun avatarGitHubUserScreen (login:String, avatarUrl:String): Screen
    fun gitHubImageScreen():Screen
}