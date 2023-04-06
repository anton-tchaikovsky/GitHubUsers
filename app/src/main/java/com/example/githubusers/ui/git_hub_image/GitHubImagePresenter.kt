package com.example.githubusers.ui.git_hub_image

import android.graphics.Bitmap
import com.example.githubusers.domain.repository.IGitHubRepository
import com.example.githubusers.utils.MESSAGE_ERROR_FILE_NOT_FOUND
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.io.FileNotFoundException

class GitHubImagePresenter(private val gitHubRepository: IGitHubRepository) :
    MvpPresenter<IGitHubImageView>() {

    private var bitmapGitHubImage: Bitmap? = null
    private val compositeDisposable = CompositeDisposable()

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
        bitmapGitHubImage?.let {
            viewState.showAlertDialog()
// Проверка для избежания дополнительного запуска процесса сохранения при уже существующем процессе.
// При завершении процесса сохранения или отмене сохранения производится compositeDisposable.clear()
            if (compositeDisposable.size() == 0) {
                subscribeToSaveGitHubImagePng(it)
                subscribeToProgressSaveGitHubImagePng()
            }
        }
    }

    private fun subscribeToProgressSaveGitHubImagePng() {
       compositeDisposable.add (gitHubRepository.getProgressSaveGitHubImagePng()
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeBy(
               onNext = {
                   viewState.setProgress(it.toInt())
               }
           ))
    }

    private fun subscribeToSaveGitHubImagePng(bitmapGitHubImage: Bitmap) {
        compositeDisposable.add(
            gitHubRepository.saveGitHubImagePng(bitmapGitHubImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onComplete = {
                        viewState.run {
                            showSuccessSave()
                            dismissAlertDialog()
                            compositeDisposable.clear()
                        }
                    },
                    onError = {
                        viewState.run {
                            showError(it)
                            dismissAlertDialog()
                        }
                        compositeDisposable.clear()
                    }
                )
        )
    }

    fun onCancelSavePng() {
        compositeDisposable.clear()
        viewState.apply {
            dismissAlertDialog()
            setProgress(0)
        }
    }

    fun onContinueSavePng() {
        viewState.dismissAlertDialog()
    }
}