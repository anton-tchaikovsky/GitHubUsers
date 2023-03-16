package com.example.githubusers.ui.avatar_git_hub_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.example.githubusers.databinding.FragmentAvatarGitHubUserBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val KEY_AVATAR_URL = "KeyAvatarUrl"
private const val KEY_LOGIN = "KeyLogin"

@Suppress("unused")
class AvatarGitHubUserFragment : MvpAppCompatFragment(), AvatarGitHubUserView {

    private var login: String? = null
    private var avatarUrl: String? = null
    private val avatarGitHubUserPresenter by moxyPresenter { AvatarGitHubUserPresenter()}

    private var _binding: FragmentAvatarGitHubUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            login = it.getString(KEY_LOGIN)
            avatarUrl = it.getString(KEY_AVATAR_URL)
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

    override fun showAvatar() {
        binding.run {
            gitHubUserLargeLogin.text = login
            gitHubUsersLargeAvatarImageView.load(avatarUrl)
        }
    }
}