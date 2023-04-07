package com.example.githubusers.navigation

import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.ui.info_about_repository_git_hub_user.InfoAboutRepositoryGitHubUserFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class RepositoriesGitHubUserScreensImpl : IRepositoriesGitHubUserScreens {

    override fun infoAboutRepositoryGitHubUserScreen(repositoryGitHubUser: RepositoryGitHubUser): Screen =
        FragmentScreen {InfoAboutRepositoryGitHubUserFragment.newInstance(repositoryGitHubUser)}

}