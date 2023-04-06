package com.example.githubusers.ui.avatar_git_hub_user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IAvatarGitHubUserView:MvpView {
fun showAvatar(login: String, avatarUrl: String)
@StateStrategyType(SkipStrategy::class)
fun showEmptyGitHubUser()
}