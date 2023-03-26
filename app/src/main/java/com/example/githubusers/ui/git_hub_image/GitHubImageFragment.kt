package com.example.githubusers.ui.git_hub_image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.api.load
import com.example.githubusers.R
import com.example.githubusers.databinding.FragmentGitHubImageBinding
import com.example.githubusers.gitHubUserApp
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.io.File

@Suppress("unused")
class GitHubImageFragment : MvpAppCompatFragment(), GitHubImageView {

    private val gitHubImagePresenter by moxyPresenter {
        GitHubImagePresenter(
            requireContext().gitHubUserApp.gitHubRepository,
            requireContext().gitHubUserApp.router
        )
    }
    private var _binding: FragmentGitHubImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGitHubImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() =
            GitHubImageFragment()
    }

    override fun showImage(fileGitHubImage: File) {
        binding.gitHubImage.load(fileGitHubImage)
    }

    override fun showNoHasImage() {
        binding.gitHubImage.load(R.drawable.ic_baseline_hide_image_24)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message.toString(), Toast.LENGTH_LONG).show()
    }

}