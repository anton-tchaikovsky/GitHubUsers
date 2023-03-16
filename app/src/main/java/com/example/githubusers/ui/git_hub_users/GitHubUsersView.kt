package com.example.githubusers.ui.git_hub_users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface GitHubUsersView:MvpView{
    fun initView()
    fun showGitHubUsers()
    @StateStrategyType(SkipStrategy::class)
    fun showItemGitHubUsers(login: String, avatarUrl: String)
    @StateStrategyType(SkipStrategy::class)
    fun showError(error: Throwable)
    fun showLoading()
    fun hideLoading()
    fun showAnimateGitHubUsers()
}