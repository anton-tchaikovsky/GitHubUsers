package com.example.githubusers.ui.info_about_repository_git_hub_user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface InfoAboutRepositoryGitHubUserView:MvpView {
    fun initView(name:String, language: String, stargazersCount: Int, watchersCount: Int, forksCount: Int)
}