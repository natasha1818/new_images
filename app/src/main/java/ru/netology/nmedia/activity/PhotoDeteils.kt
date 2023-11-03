package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import ru.netology.nmedia.BuildConfig
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.NewPostFragment.Companion.textArg
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

        fun photodisplay (post:Post) {
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
            }}

        binding.callback.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
}}