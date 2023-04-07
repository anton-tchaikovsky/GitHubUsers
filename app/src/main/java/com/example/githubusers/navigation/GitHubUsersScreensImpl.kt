package com.example.githubusers.navigation

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.ui.avatar_git_hub_user.AvatarGitHubUserFragment
import com.example.githubusers.ui.git_hub_image.GitHubImageFragment
import com.example.githubusers.ui.repositories_git_hub_user.RepositoriesGitHubUserFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class GitHubUsersScreensImpl : IGitHubUsersScreens {
    override fun avatarGitHubUserScreen(login: String, avatarUrl: String): Screen =
        FragmentScreen { AvatarGitHubUserFragment.newInstance(login, avatarUrl) }

    override fun gitHubImageScreen(): Screen =
        FragmentScreen { GitHubImageFragment.newInstance() }

    override fun repositoriesGitHubUserScreen(
        gitHubUser: GitHubUser,
        repositoriesGitHubUser: List<RepositoryGitHubUser>
    ): Screen =
        FragmentScreen {
            RepositoriesGitHubUserFragment.newInstance(
                gitHubUser,
                repositoriesGitHubUser
            )
        }
}