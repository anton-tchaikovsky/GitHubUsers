package com.example.githubusers.ui.git_hub_users.git_nub_users_recycle_view

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.ui.recycle_view.IItemPresenterRecycleView

interface IItemGitHubUsersPresenter: IItemPresenterRecycleView<IItemGitHubUsersView, GitHubUser> {
    var avatarClickListener: ((IItemGitHubUsersView)->Unit)?
    var openRepositoriesButtonClickListener: ((IItemGitHubUsersView)->Unit)?
}