package com.example.githubusers.ui.repositories_git_hub_user.repositories_git_hub_user_recycle_view

import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.ui.recycle_view.IItemPresenterRecycleView

interface IItemRepositoriesGitHubUserPresenter:
    IItemPresenterRecycleView<IItemRepositoriesGitHubUserView, RepositoryGitHubUser> {
var itemClickListener: ((IItemRepositoriesGitHubUserView) -> Unit)?
}