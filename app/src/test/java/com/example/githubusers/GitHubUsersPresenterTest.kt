package com.example.githubusers

import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.repository.GitHubUsersRepository
import com.example.githubusers.ui.GitHubUsersContract
import com.example.githubusers.ui.GitHubUsersPresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.verify
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertSame
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

class GitHubUsersPresenterTest {

    private lateinit var presenter: GitHubUsersPresenter

    @Mock
    private lateinit var view: GitHubUsersContract.GitHubUsersView

    @Mock
    private lateinit var repository: GitHubUsersRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = GitHubUsersPresenter(repository)
    }

    @Test
    fun attach_FirstAttach_Test() {
        assertNull(presenter.gitHubUsersView)
        assertNull(presenter.gitHubUsersList)
        assertNull(presenter.isShowLoading)
        presenter.attach(view)
        assertSame(presenter.gitHubUsersView, view)
        assertNotNull(presenter.gitHubUsersView)
        assertNull(presenter.gitHubUsersList)
        assertNull(presenter.isShowLoading)
        verify(view, Mockito.never()).showGitHubUsers(any())
        verify(view, Mockito.never()).showLoading(any())
    }

    @Test
    fun attach_AttachWithGitHubUserListNotNullIsShowLoadingNull() {
        presenter.gitHubUsersList = DEFAULT_GIT_HAB_USERS_LIST
        presenter.attach(view)
        verify(view, Mockito.times(1)).showGitHubUsers(DEFAULT_GIT_HAB_USERS_LIST)
        verify(view, Mockito.never()).showLoading(any())
    }

    @Test
    fun attach_AttachWithGitHubUserListNotNullIsShowLoadingTrue() {
        presenter.gitHubUsersList = DEFAULT_GIT_HAB_USERS_LIST
        presenter.isShowLoading = true
        presenter.attach(view)
        inOrder(view).apply {
            verify(view, Mockito.times(1)).showGitHubUsers(DEFAULT_GIT_HAB_USERS_LIST)
            verify(view, Mockito.times(1)).showLoading(true)
        }
    }

    @Test
    fun attach_AttachWithGitHubUserListNotNullIsShowLoadingFalse() {
        presenter.gitHubUsersList = DEFAULT_GIT_HAB_USERS_LIST
        presenter.isShowLoading = false
        presenter.attach(view)
        inOrder(view).apply {
            verify(view, Mockito.times(1)).showGitHubUsers(DEFAULT_GIT_HAB_USERS_LIST)
            verify(view, Mockito.times(1)).showLoading(false)
        }
    }

    @Test
    fun attach_AttachWithGitHubUserListNullIsShowLoadingTrue() {
        presenter.isShowLoading = true
        presenter.attach(view)
        verify(view, Mockito.never()).showGitHubUsers(any())
        verify(view, Mockito.times(1)).showLoading(true)
    }

    @Test
    fun attach_AttachWithGitHubUserListNullIsShowLoadingFalse() {
        presenter.isShowLoading = false
        presenter.attach(view)
        verify(view, Mockito.never()).showGitHubUsers(any())
        verify(view, Mockito.times(1)).showLoading(false)
    }

    @Test
    fun detach_Test() {
        presenter.gitHubUsersView = view
        presenter.gitHubUsersList = DEFAULT_GIT_HAB_USERS_LIST
        presenter.isShowLoading = true
        presenter.detach()
        assertNull(presenter.gitHubUsersView)
        assertSame(presenter.gitHubUsersList, DEFAULT_GIT_HAB_USERS_LIST)
        assertSame(presenter.isShowLoading, true)
    }

    @Test
    fun onRequestGitHubUsers_Test() {
        presenter.attach(view)
        presenter.onRequestGitHubUsers()
        verify(view, Mockito.times(1)).showLoading(true)
        verify(repository, Mockito.times(1)).getGitHubUsers(any(), any())
        assertSame(presenter.isShowLoading, true)
    }

    @Test
    fun onSuccessLoadingGitHubUsers_Test() {
        presenter.attach(view)
        presenter.onSuccessLoadingGitHubUsers(DEFAULT_GIT_HAB_USERS_LIST)
        inOrder(view).apply {
            verify(view, Mockito.times(1)).showLoading(false)
            verify(view, Mockito.times(1)).showGitHubUsers(DEFAULT_GIT_HAB_USERS_LIST)
        }
        assertSame(presenter.isShowLoading, false)
        assertSame(presenter.gitHubUsersList, DEFAULT_GIT_HAB_USERS_LIST)
    }

    @Test
    fun onErrorLoadingGitHubUsers_Test() {
        presenter.attach(view)
        presenter.onErrorLoadingGitHubUsers(ERROR)
        inOrder(view).apply {
            verify(view, Mockito.times(1)).showLoading(false)
            verify(view, Mockito.times(1)).showError(ERROR)
        }
        assertSame(presenter.isShowLoading, false)
        assertSame(presenter.gitHubUsersList, null)
    }

    companion object {
        private val DEFAULT_GIT_HAB_USERS_LIST = listOf(
            GitHubUser(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
            GitHubUser(2, "defunkt", "https://avatars.githubusercontent.com/u/2?v=4"),
            GitHubUser(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4"),
            GitHubUser(4, "wycats", "https://avatars.githubusercontent.com/u/4?v=4"),
            GitHubUser(5, "ezmobius", "https://avatars.githubusercontent.com/u/5?v=4")
        )
        private val ERROR = IOException()
    }


}