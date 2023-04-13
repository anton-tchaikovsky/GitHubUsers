package com.example.githubusers.ui.repositories_git_hub_user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IRepositoriesGitHubUserView:MvpView {
    fun initView(login: String)
    fun showRepositories()
    @StateStrategyType(SkipStrategy::class)
    fun showError(error: Throwable)
}