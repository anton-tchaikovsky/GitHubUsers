package com.example.githubusers.ui.repositories_git_hub_user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.FragmentRepositoriesGitHubUserBinding
import com.example.githubusers.domain.dto.GitHubUser
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import com.example.githubusers.gitHubUserApp
import com.example.githubusers.ui.git_hub_users.GitHubUsersAppScreensImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val KEY_GIT_HUB_USER = "KeyGitHubUser"
private const val KEY_REPOSITORIES_GIT_HUB_USER = "KeyRepositoriesGitHubUser"

class RepositoriesGitHubUserFragment : MvpAppCompatFragment(), RepositoriesGitHubUserView {

    private val repositoriesGitHubUserPresenter by moxyPresenter {
        RepositoriesGitHubUserPresenter(requireContext().gitHubUserApp.router, GitHubUsersAppScreensImpl())
    }
    private val repositoriesGitHubUserAdapter by lazy {
        RepositoriesGitHubUserAdapter(
            repositoriesGitHubUserPresenter.itemRepositoriesGitHubUserPresenter
        )
    }
    private var _binding: FragmentRepositoriesGitHubUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            repositoriesGitHubUserPresenter.run {
                gitHubUser = it.getParcelable(KEY_GIT_HUB_USER)
                it.getParcelableArrayList<RepositoryGitHubUser>(KEY_REPOSITORIES_GIT_HUB_USER)
                    ?.let {
                        repositoriesGitHubUser = it.toList()
                    }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesGitHubUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(
            gitHubUser: GitHubUser,
            repositoriesGitHubUser: List<RepositoryGitHubUser>
        ) =
            RepositoriesGitHubUserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GIT_HUB_USER, gitHubUser)
                    putParcelableArrayList(
                        KEY_REPOSITORIES_GIT_HUB_USER,
                        ArrayList(repositoriesGitHubUser)
                    )
                }
            }
    }

    override fun initView(login: String) {
        initLoginTextView(login)
        initRepositoriesRecycleView()
    }

    private fun initLoginTextView(login: String) {
        binding.login.text = login
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showRepositories() {
        repositoriesGitHubUserAdapter.notifyDataSetChanged()
    }

    private fun initRepositoriesRecycleView() {
        binding.repositoriesRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = repositoriesGitHubUserAdapter
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}