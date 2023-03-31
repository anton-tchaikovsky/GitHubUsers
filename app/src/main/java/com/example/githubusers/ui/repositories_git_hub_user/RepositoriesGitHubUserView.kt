package com.example.githubusers.ui.repositories_git_hub_user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoriesGitHubUserView:MvpView {
    fun initView(login: String)
    fun showRepositories()
}