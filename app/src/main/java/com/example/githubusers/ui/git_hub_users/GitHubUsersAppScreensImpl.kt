package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.ui.avatar_git_hub_user.AvatarGitHubUserFragment
import com.example.githubusers.ui.git_hub_image.GitHubImageFragment
import com.example.githubusers.ui.info_about_repository_git_hub_user.InfoAboutRepositoryGitHubUserFragment
import com.example.githubusers.ui.info_about_repository_git_hub_user.InfoAboutRepositoryGitHubUserView
import com.example.githubusers.ui.repositories_git_hub_user.RepositoriesGitHubUserFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class GitHubUsersAppScreensImpl : GitHubUsersAppScreens {
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

    override fun infoAboutRepositoryGitHubUserScreen(repositoryGitHubUser: RepositoryGitHubUser): Screen =
        FragmentScreen {InfoAboutRepositoryGitHubUserFragment.newInstance(repositoryGitHubUser)}

}