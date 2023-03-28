package com.example.githubusers.ui.git_hub_image

import android.graphics.Bitmap
import com.example.githubusers.domain.repository.GitHubRepository
import com.example.githubusers.utils.MESSAGE_ERROR_FILE_NOT_FOUND
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.io.FileNotFoundException

class GitHubImagePresenter(private val gitHubRepository: GitHubRepository) :
    MvpPresenter<GitHubImageView>() {

    private var bitmapGitHubImage: Bitmap? = null
    private var disposableSaveGitHubImagePng: Disposable? = null

    override fun onFirstViewAttach() {
        viewState.initView()
        subscribeToReadFileGitHubImage()
        super.onFirstViewAttach()
    }

    private fun subscribeToReadFileGitHubImage() {
        viewState.run {
            gitHubRepository.readGitHubImage()
                .subscribeBy(
                    onSuccess = {
                        bitmapGitHubImage = it
                        showImage(it)
                    },
                    onComplete = {
                        showNoHasImage()
                        showError(FileNotFoundException(MESSAGE_ERROR_FILE_NOT_FOUND))
                    },
                    onError = {
                        showNoHasImage()
                        showError(it)
                    }
                )
        }
    }

    fun onSavePng() {
        viewState.run {
            showAlertDialog()
            bitmapGitHubImage?.let {
                disposableSaveGitHubImagePng =
                    gitHubRepository.saveGitHubImagePng(bitmapGitHubImage!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                            onComplete = {
                                showSuccessSave()
                                dismissAlertDialog()
                            },
                            onError = {
                                showError(it)
                                dismissAlertDialog()
                            }
                        )
            }
        }
    }

    fun onCancelSavePng() {
        disposableSaveGitHubImagePng?.dispose()
        viewState.dismissAlertDialog()
    }

    fun onContinueSavePng() {
        viewState.dismissAlertDialog()
    }
}