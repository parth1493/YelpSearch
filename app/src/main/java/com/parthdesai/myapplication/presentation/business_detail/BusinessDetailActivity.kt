package com.parthdesai.myapplication.presentation.business_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.parthdesai.myapplication.R
import com.parthdesai.myapplication.data.models.BusinessDto
import timber.log.Timber
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.parthdesai.myapplication.databinding.ActivityBusinessDetailBinding


class BusinessDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusinessDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_business_detail)
        setup()
        if (intent.extras != null) {
            val business: BusinessDto? = intent.getParcelableExtra("business")
            business?.let { businessDto ->
                val categories: String = TextUtils.join(", ", businessDto.categories)
                binding.businessTitle.text = businessDto.name
                binding.businessCategory.text = "Categories: $categories"
                binding.businessRating.text = "Rating: ${businessDto.rating}"

                Glide
                    .with(binding.businessImg)
                    .load(businessDto.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.businessImg)
            }
        }
    }

    private fun setup() {
        binding = ActivityBusinessDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Business Detail"
    }
}