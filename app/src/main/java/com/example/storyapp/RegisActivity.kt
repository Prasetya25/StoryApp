package com.example.storyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.storyapp.ViewModel.LoginViewModel
import com.example.storyapp.ViewModel.RegisViewModel
import com.example.storyapp.databinding.ActivityRegisBinding

class RegisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisBinding
    private lateinit var viewModel: RegisViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)
        // Binding
        binding = ActivityRegisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // ViewModel
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[RegisViewModel::class.java]

        binding.btDaftar.setOnClickListener{
            viewModel.setRegis(binding.txUsername.text.toString(),
                binding.txEmail.text.toString(), binding.txPassword.text.toString())
            Intent(this@RegisActivity, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }


    }
}