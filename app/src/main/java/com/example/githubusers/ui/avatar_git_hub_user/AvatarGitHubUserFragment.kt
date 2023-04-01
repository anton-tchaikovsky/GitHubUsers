package com.example.githubusers.ui.avatar_git_hub_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.api.load
import com.example.githubusers.databinding.FragmentAvatarGitHubUserBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val KEY_AVATAR_URL = "KeyAvatarUrl"
private const val KEY_LOGIN = "KeyLogin"
private const val EMPTY_GIT_HUB_USER = "Empty GitHub user"

@Suppress("unused")
class AvatarGitHubUserFragment : MvpAppCompatFragment(), AvatarGitHubUserView {

    private val avatarGitHubUserPresenter by moxyPresenter { AvatarGitHubUserPresenter()}
    private var _binding: FragmentAvatarGitHubUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            avatarGitHubUserPresenter.apply {
                login = it.getString(KEY_LOGIN)
                avatarUrl = it.getString(KEY_AVATAR_URL)
            }.also {
                it.onInitView()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAvatarGitHubUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(login: String, avatarUrl: String) =
            AvatarGitHubUserFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_AVATAR_URL, avatarUrl)
                    putString(KEY_LOGIN, login)
                }
            }
    }

    override fun showAvatar(login: String, avatarUrl: String) {
        binding.run {
            gitHubUserLargeLogin.text = login
            gitHubUsersLargeAvatarImageView.load(avatarUrl)
        }
    }

    override fun showEmptyGitHubUser() {
        Toast.makeText(requireContext(), EMPTY_GIT_HUB_USER, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}