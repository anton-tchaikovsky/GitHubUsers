package com.example.githubusers.ui.git_hub_users

import com.example.githubusers.domain.dto.GitHubUser

interface ItemGitHubUsersPresenter:ItemPresenterRecycleView<ItemGitHubUsersView, GitHubUser> {
    var avatarClickListener: ((ItemGitHubUsersView)->Unit)?
    var openRepositoriesButtonClickListener: ((ItemGitHubUsersView)->Unit)?
}