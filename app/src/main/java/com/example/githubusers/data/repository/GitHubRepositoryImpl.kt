package com.example.githubusers.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.githubusers.data.api.RemoteDataSourceGitHubImage
import com.example.githubusers.data.api.RemoteDataSourceGitHubUsers
import com.example.githubusers.data.network.INetWorkStatus
import com.example.githubusers.data.room.GitHubUsersCache
import com.example.githubusers.data.room.IGitHubUsersCache
import com.example.githubusers.data.room.IRepositoriesGitHubUserCache
import com.example.githubusers.data.room.RepositoriesGitHubUserCache
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.domain.repository.GitHubRepository
import com.example.githubusers.utils.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit

class GitHubRepositoryImpl(private val netWorkStatus: INetWorkStatus) : GitHubRepository {

    private val path: File = Environment.getExternalStorageDirectory()
    private val fileJpg = File(path, GIT_HUB_IMAGE_JPG)
    private val filePng = File(path, GIT_HUB_IMAGE_PNG)
    private val qualityCompressToPng = 100
    private val remoteDataSourceGitHubUsers: RemoteDataSourceGitHubUsers by lazy {
        RemoteDataSourceGitHubUsers()
    }
    private val remoteDataSourceGitHubImage: RemoteDataSourceGitHubImage by lazy {
        RemoteDataSourceGitHubImage()
    }

    private val gitHubUsersCache: IGitHubUsersCache by lazy {
        GitHubUsersCache()
    }

    private val repositoriesGitHubUserCache: IRepositoriesGitHubUserCache by lazy {
        RepositoriesGitHubUserCache()
    }

    override fun getGitHubUsers(): Single<List<GitHubUser>> =
        netWorkStatus.isConnectSingle()
            .flatMap { isConnect ->
                if (isConnect) {
                    remoteDataSourceGitHubUsers.callAPIGitHubUsers()
                        .flatMap { gitHubUsers ->
                            gitHubUsersCache.saveToCache(gitHubUsers)
                                .subscribeBy(
                                    onError = {
                                        it.printStackTrace()
                                    }
                                )
                            Single.fromCallable {
                               return@fromCallable gitHubUsers
                            }
                        }
                } else {
                    return@flatMap gitHubUsersCache.readFromCache()
                }

            }
            .subscribeOn(Schedulers.io())

    override fun getRepositoriesGitHubUser(gitHubUser: GitHubUser): Single<List<RepositoryGitHubUser>> =
        netWorkStatus.isConnectSingle()
            .flatMap { isConnect ->
                if (isConnect){
                    remoteDataSourceGitHubUsers.callAPIRepositoriesGitHubUser(gitHubUser.reposUrl)
                        .flatMap { repositoriesGitHubUser ->
                            Single.fromCallable {
                                repositoriesGitHubUserCache.saveToCache(
                                    gitHubUser,
                                    repositoriesGitHubUser
                                )
                                    .subscribeBy(
                                        onError = {
                                            it.printStackTrace()
                                        }
                                    )
                                return@fromCallable repositoriesGitHubUser
                            }
                        }
                } else {
                    return@flatMap repositoriesGitHubUserCache.readFromCache(gitHubUser)
                }
            }
            .subscribeOn(Schedulers.io())

    override fun getDefaultGitHubUsers(): Observable<List<GitHubUser>> {
        return Observable.intervalRange(0, 5, 0, 10, TimeUnit.MILLISECONDS)
            .switchMap {
                return@switchMap when (it) {
                    0L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .map { defaultGitHubUsersList ->
                            listOf(defaultGitHubUsersList[0])
                        }
                        .delay(6L, TimeUnit.SECONDS)
                    1L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .map { defaultGitHubUsersList ->
                            listOf(defaultGitHubUsersList[0], defaultGitHubUsersList[1])
                        }
                        .delay(5L, TimeUnit.SECONDS)
                    2L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .map { defaultGitHubUsersList ->
                            listOf(
                                defaultGitHubUsersList[0],
                                defaultGitHubUsersList[1],
                                defaultGitHubUsersList[2]
                            )
                        }
                        .delay(4L, TimeUnit.SECONDS)
                    3L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .map { defaultGitHubUsersList ->
                            listOf(
                                defaultGitHubUsersList[0],
                                defaultGitHubUsersList[1],
                                defaultGitHubUsersList[2],
                                defaultGitHubUsersList[3]
                            )
                        }
                        .delay(3L, TimeUnit.SECONDS)
                    4L -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                        .delay(2L, TimeUnit.SECONDS)
                    else -> Observable.just(DEFAULT_GIT_HAB_USERS_LIST)
                }
            }
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

}