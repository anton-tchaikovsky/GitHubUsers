package com.example.githubusers.ui.git_hub_image

import android.app.AlertDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.githubusers.GitHubUsersApp
import com.example.githubusers.R
import com.example.githubusers.databinding.FragmentGitHubImageBinding
import com.example.githubusers.ui.image.GlideImageLoader
import com.example.githubusers.utils.DURATION_SAVE_GIT_HUB_IMAGE_PNG
import com.google.android.material.progressindicator.LinearProgressIndicator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

@Suppress("unused")
class GitHubImageFragment : MvpAppCompatFragment(), IGitHubImageView {

    private val gitHubImagePresenter by moxyPresenter {
        GitHubImagePresenter(
            GitHubUsersApp.instance.gitHubRepository, AndroidSchedulers.mainThread()
        )
    }
    private var _binding: FragmentGitHubImageBinding? = null
    private val binding get() = _binding!!

    private val imageLoader = GlideImageLoader()

    private val progressIndicator: LinearProgressIndicator by lazy {
        LinearProgressIndicator(requireContext()).apply {
            max = DURATION_SAVE_GIT_HUB_IMAGE_PNG.toInt()
            progress = 0
        }
    }

    private val alertDialog: AlertDialog by lazy {
        AlertDialog.Builder(requireContext())
            .setMessage(MESSAGE_PROCESS_OF_SAVING_IN_PNG)
            .setNegativeButton(
                android.R.string.cancel
            ) { _, _ -> gitHubImagePresenter.onCancelSavePng() }
            .setPositiveButton(
                android.R.string.ok
            ) { _, _ -> gitHubImagePresenter.onContinueSavePng() }
            .setView(progressIndicator)
            .create()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGitHubImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val MESSAGE_FOR_SAVED_SUCCESSFULLY= "The file was saved successfully"
        const val MESSAGE_PROCESS_OF_SAVING_IN_PNG= "File in the process of saving in png"

        fun newInstance() =
            GitHubImageFragment()
    }

    override fun initView() {
        binding.saveGitHubImagePngButton.setOnClickListener {
            gitHubImagePresenter.onSavePng()
        }
    }

    override fun showImage(bitmapGitHubImage: Bitmap) {
        imageLoader.loadImageInto(bitmapGitHubImage, binding.gitHubImage)
    }

    override fun showNoHasImage() {
        binding.run {
            saveGitHubImagePngButton.visibility = View.GONE
            imageLoader.loadImageInto(R.drawable.ic_baseline_hide_image_24, gitHubImage)
        }
    }

    override fun showAlertDialog() {
        alertDialog.show()
    }

    override fun dismissAlertDialog() {
        alertDialog.dismiss()
    }

    override fun setProgress(progress: Int) {
        progressIndicator.progress = progress
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showSuccessSave() {
        Toast.makeText(requireContext(), MESSAGE_FOR_SAVED_SUCCESSFULLY, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}