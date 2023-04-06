package com.example.githubusers.ui.info_about_repository_git_hub_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubusers.databinding.FragmentInfoAboutRepositoryGitHubUserBinding
import com.example.githubusers.domain.dto.RepositoryGitHubUser
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class InfoAboutRepositoryGitHubUserFragment : MvpAppCompatFragment(),
    IInfoAboutRepositoryGitHubUserView {

    private val repositoriesGitHubUserPresenter by moxyPresenter {
        InfoAboutRepositoryGitHubUserPresenter()
    }

    private var _binding: FragmentInfoAboutRepositoryGitHubUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            repositoriesGitHubUserPresenter.repositoryGitHubUser =
                it.getParcelable(KEY_REPOSITORY_GIT_HUB_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoAboutRepositoryGitHubUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        private const val KEY_REPOSITORY_GIT_HUB_USER = "KeyRepositoryGitHubUser"

        fun newInstance(repositoryGitHubUser: RepositoryGitHubUser) =
            InfoAboutRepositoryGitHubUserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_REPOSITORY_GIT_HUB_USER, repositoryGitHubUser)
                }
            }
    }

    override fun initView(
        name: String,
        language: String,
        stargazersCount: Int,
        watchersCount: Int,
        forksCount: Int
    ) {
        binding.run {
            this.name.text = name
            this.language.text = language
            this.stargazersCount.text = stargazersCount.toString()
            this.watchersCount.text = watchersCount.toString()
            this.forksCount.text = forksCount.toString()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}