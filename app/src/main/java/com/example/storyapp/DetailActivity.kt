package com.example.storyapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.storyapp.Datauser.DetailStory
import com.example.storyapp.ViewModel.DetailViewModel
import com.example.storyapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var detailStory: DetailStory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_detail)

//        val nama = intent.getStringExtra(NAMA_X)
//        val photo = intent.getStringExtra(PHOTO_X)
//        val bundle = Bundle()
//        bundle.putString(NAMA_X,nama)
//        bundle.putString(PHOTO_X,photo)

        detailStory = intent.getParcelableExtra<DetailStory>(EXTRA_USER) as DetailStory
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

//        viewModel.setDetailStory(nama.toString())
        viewModel.setDetailStory(detailStory.id.toString())
        Log.e(TAG, detailStory.id)
        viewModel.getDetailStory().observe(this){
            binding.apply {
                Glide.with(this@DetailActivity)
                .load(detailStory.photoUrl)
                .into(binding.imgStory)
                tvNamaUploader.text = detailStory.name
                tvDeskripsi.text = detailStory.description
                tvCreate.text = detailStory.createdAt
                tvId.text = detailStory.id
            }
        }


    }

    companion object {
        val EXTRA_USER = "EXTRA_USER"
        const val NAMA_X = "extra_user"
        const val PHOTO_X = "extra_photo"
    }
}