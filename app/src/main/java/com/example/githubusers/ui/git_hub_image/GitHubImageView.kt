package com.example.githubusers.ui.git_hub_image

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.io.File

@StateStrategyType(AddToEndSingleStrategy::class)
interface GitHubImageView:MvpView {
    fun initView()
    fun showImage(bitmapGitHubImage: Bitmap)
    fun showNoHasImage()
    @StateStrategyType(SkipStrategy::class)
    fun showError(error: Throwable)
    @StateStrategyType(SkipStrategy::class)
    fun showSuccessSave()
}