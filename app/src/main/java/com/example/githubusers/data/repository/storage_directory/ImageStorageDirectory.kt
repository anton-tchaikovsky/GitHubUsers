package com.example.githubusers.data.repository.storage_directory

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.githubusers.domain.repository.storage_directory.IImageStorageDirectory
import com.example.githubusers.utils.DURATION_SAVE_GIT_HUB_IMAGE_PNG
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageStorageDirectory (private val fileJpg: File, private val filePng: File): IImageStorageDirectory {

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
        private const val qualityCompressToPng = 100
        const val MESSAGE_ERROR_PERMISSION_TO_READ = "There is no permission to read the file."
        const val MESSAGE_ERROR_READ_JPG = "The file is not being read"
        const val MESSAGE_ERROR_CONVERSION_TO_PNG = "File conversion and saving error"
    }

}