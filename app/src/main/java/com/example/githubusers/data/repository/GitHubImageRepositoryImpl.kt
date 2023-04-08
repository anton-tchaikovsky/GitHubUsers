package com.example.githubusers.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.githubusers.data.api.RemoteDataSourceGitHubImage
import com.example.githubusers.domain.repository.IGitHubImageRepository
import com.example.githubusers.utils.DURATION_SAVE_GIT_HUB_IMAGE_PNG
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit

class GitHubImageRepositoryImpl:
    IGitHubImageRepository {

    private val path: File = Environment.getExternalStorageDirectory()
    private val fileJpg = File(path, GIT_HUB_IMAGE_JPG)
    private val filePng = File(path, GIT_HUB_IMAGE_PNG)
    private val qualityCompressToPng = 100

    private val remoteDataSourceGitHubImage: RemoteDataSourceGitHubImage by lazy {
        RemoteDataSourceGitHubImage()
    }

    override fun loadGitHubImage(): Single<ResponseBody> =
        remoteDataSourceGitHubImage.callAPIGitHubImage()

    override fun saveGitHubImageJpg(responseBodyGitHubImage: ResponseBody): Completable {
        return Completable.fromAction {
            var fileOutputStream: FileOutputStream? = null
            try {
                fileOutputStream = FileOutputStream(fileJpg)
                fileOutputStream.write(responseBodyGitHubImage.bytes())
            } catch (e: Exception) {
                throw e
            } finally {
                try {
                    fileOutputStream?.close()
                } catch (e: IOException) {
                    throw e
                }
            }
        }
    }

    override fun saveGitHubImagePng(gitHubImage: Bitmap): Completable {
        return Completable.create {
            var fileOutputStream: FileOutputStream? = null
// Открываем поток записи.
// При возникновении исключения, если есть наблюдатель, испускаем onError, иначе пишем printStackTrace().
            try {
                fileOutputStream = FileOutputStream(filePng)
            } catch (e: Exception) {
                if (it.isDisposed)
                    e.printStackTrace()
                else
                    it.onError(e)
            }
// Обрабатываем уничтожение наблюдателя: закрываем поток записи.
// При возникновении исключения пишем  printStackTrace().
            it.setCancellable {
                try {
                    fileOutputStream?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
// Иммитируем длительный процесс сохранения.
// При возникновении исключения, если есть наблюдатель, испускаем onError,
// иначе пишем printStackTrace().
            try {
                Thread.sleep(DURATION_SAVE_GIT_HUB_IMAGE_PNG)
            } catch (e: InterruptedException) {
                if (it.isDisposed)
                    e.printStackTrace()
                else
                    it.onError(e)
            }
// Сохраняеи Png.
// При успешном сохранении,если есть наблюдатель, испускаем onComplete(),
// при неуспешном,если есть наблюдатель - испускаем onError.
            if (gitHubImage.compress(
                    Bitmap.CompressFormat.PNG,
                    qualityCompressToPng,
                    fileOutputStream
                ) && !it.isDisposed
            )
                it.onComplete()
            else if (!it.isDisposed)
                it.onError(IllegalArgumentException(MESSAGE_ERROR_CONVERSION_TO_PNG))
// Закрываем поток записи, если он есть.
// При возникновении исключения, если есть наблюдатель, испускаем onError, иначе пишем printStackTrace().
            try {
                fileOutputStream?.close()
            } catch (e: IOException) {
                if (it.isDisposed)
                    e.printStackTrace()
                else
                    it.onError(e)
            }
        }
    }

    override fun getProgressSaveGitHubImagePng(): Observable<Long> {
        return Observable.intervalRange(0, 5001, 0, 1, TimeUnit.MILLISECONDS)
    }

    override fun readGitHubImage(): Maybe<Bitmap> {
        return Maybe.create {
            if (!fileJpg.exists())
                it.onComplete()
            else if (fileJpg.canRead()) {
                val bitmapGitHubImage = BitmapFactory.decodeFile(fileJpg.absolutePath)
                if (bitmapGitHubImage != null)
                    it.onSuccess(bitmapGitHubImage)
                else
                    it.onError(IOException(MESSAGE_ERROR_READ_JPG))
            } else
                it.onError(IOException(MESSAGE_ERROR_PERMISSION_TO_READ))
        }
    }

    companion object{
        const val MESSAGE_ERROR_PERMISSION_TO_READ = "There is no permission to read the file."
        const val MESSAGE_ERROR_READ_JPG = "The file is not being read"
        const val MESSAGE_ERROR_CONVERSION_TO_PNG = "File conversion and saving error"
        const val GIT_HUB_IMAGE_JPG = "git_hub.jpg"
        const val GIT_HUB_IMAGE_PNG = "git_hub.png"
    }

}