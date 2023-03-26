package com.example.githubusers.ui.git_hub_image

import com.example.githubusers.domain.repository.GitHubRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class GitHubImagePresenter(private val gitHubRepository: GitHubRepository, private val router: Router) :
    MvpPresenter<GitHubImageView>() {

    override fun onFirstViewAttach() {
        subscribeToFileGitHubImage()
        super.onFirstViewAttach()
    }

    private fun subscribeToFileGitHubImage() {
        viewState.run {
            gitHubRepository.readGitHubImage()
                .subscribeBy(
                    onSuccess = {
                        showImage(it)
                    },
                    onComplete = {
                        showNoHasImage()
                    },
                    onError = {
                        showError(it)
                        router.exit()
                    }
                )
        }
    }
}