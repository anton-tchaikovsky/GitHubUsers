package com.example.githubusers.ui.repositories_git_hub_user

import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.ui.git_hub_users.ItemPresenterRecycleView

interface ItemRepositoriesGitHubUserPresenter:ItemPresenterRecycleView<ItemRepositoriesGitHubUserView, RepositoryGitHubUser> {
var itemClickListener: ((ItemRepositoriesGitHubUserView) -> Unit)?
}