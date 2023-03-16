package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.ui.avatar_git_hub_user.AvatarGitHubUserFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class GitHubUsersAppScreensImpl : GitHubUsersAppScreens {
    override fun avatarGitHubUserScreen(login: String, avatarUrl: String): Screen =
        FragmentScreen { AvatarGitHubUserFragment.newInstance(login, avatarUrl) }
}