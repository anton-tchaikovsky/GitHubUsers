package com.example.githubusers.ui

import com.example.githubusers.domain.dto.GitHubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface GitHubUsersView:MvpView{
    fun showGitHubUsers(gitHubUsers: List<GitHubUser>)
    @StateStrategyType(SkipStrategy::class)
    fun showError(error: Throwable)
    fun showLoading()
    fun hideLoading()
    fun showAnimateGitHubUsers()
}