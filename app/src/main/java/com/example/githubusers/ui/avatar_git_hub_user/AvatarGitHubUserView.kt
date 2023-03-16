package com.example.githubusers.ui.avatar_git_hub_user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AvatarGitHubUserView:MvpView {
fun showAvatar()
}