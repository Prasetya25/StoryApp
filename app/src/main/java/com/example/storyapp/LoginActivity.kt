package com.example.storyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.storyapp.Session.ManageSession
import com.example.storyapp.ViewModel.LoginViewModel
import com.example.storyapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var session: ManageSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Session
        session = ManageSession(this)
        // ViewModel
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[LoginViewModel::class.java]

        viewModel.token.observe(this@LoginActivity){
            if (it != null){
                session.simpanToken(it)
                Intent(this@LoginActivity, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }



        binding.btLogin.setOnClickListener{
            viewModel.setLogin(binding.txEmail.text.toString(), binding.txPassword.text.toString())
        }

        binding.btRegistrasi.setOnClickListener{
            Intent(this@LoginActivity, RegisActivity::class.java).also {
                startActivity(it)
            }
        }


    }
}