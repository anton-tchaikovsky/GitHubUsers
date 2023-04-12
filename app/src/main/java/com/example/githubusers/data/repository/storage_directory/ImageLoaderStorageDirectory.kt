package com.example.githubusers.data.repository.storage_directory

import com.example.githubusers.domain.repository.storage_directory.IImageLoaderStorageDirectory
import io.reactivex.rxjava3.core.Completable
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageLoaderStorageDirectory (private val fileJpg: File):IImageLoaderStorageDirectory {

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

}