package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.BuildConfig
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.NewPostFragment.Companion.textArg
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostViewHolder
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.databinding.FragmentDetailsBinding
import ru.netology.nmedia.dto.Media
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel



class PhotoDeteils :Fragment(){
    companion object {
        var Bundle.idArg: String? by StringArg
    }


    private val viewModel: PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val id = requireArguments().getString("idArg").let { requireNotNull(it) }

         viewModel.data.observe(viewLifecycleOwner) { feedModel ->
            val post = feedModel.posts.find { it.id == id.toLong() } ?: return@observe
                  if (post.attachment != null) {
                    val url2= "${BuildConfig.BASE_URL}/media/${post.attachment.url}"
                    Glide.with(binding.containerDeteils)
                        .load(url2)
                        .placeholder(R.drawable.baseline_11)
                        .error(R.drawable.ic_camera_24dp)
                        .timeout(10_000)
                        .into(binding.containerDeteils)
                } else {
                    binding.containerDeteils.visibility = View.GONE
                }



             }





        binding.callback.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
}}