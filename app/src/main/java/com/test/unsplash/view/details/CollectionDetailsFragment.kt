package com.test.unsplash.view.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.test.unsplash.R
import com.test.unsplash.databinding.FragmentCollectionDetailsBinding

class CollectionDetailsFragment : Fragment(R.layout.fragment_collection_details) {

    private lateinit var binding: FragmentCollectionDetailsBinding
    private val args by navArgs<CollectionDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCollectionDetailsBinding.bind(view)
        setupViews()
    }

    private fun setupViews() {
        val collection = args.collection
        binding.run {
            ivCover.setBackgroundColor(Color.parseColor(collection.coverPhoto.color))
            tvTitle.text = collection.title
            tvDescription.text = collection.description
            tvDescription.isVisible = collection.description != null
            tvTotalPhotos.text = String.format(
                getString(R.string.fragment_collection_details_total_photos_format),
                collection.totalPhotos
            )
            Glide.with(ivCover)
                .load(collection.coverPhoto.urls.regular)
                .into(ivCover)
        }
    }

}