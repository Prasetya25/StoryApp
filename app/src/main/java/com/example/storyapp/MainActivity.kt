package com.example.storyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.arch.core.executor.DefaultTaskExecutor
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp.Adapter.ListStoryAdapter
import com.example.storyapp.Datauser.DetailStory
import com.example.storyapp.Datauser.Story
import com.example.storyapp.Session.ManageSession
import com.example.storyapp.ViewModel.MainViewModel
import com.example.storyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapterStory: ListStoryAdapter
    private lateinit var session: ManageSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
        session = ManageSession(this)

        if (session.ambilToken() == null) {
            Intent(this@MainActivity, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        adapterStory = ListStoryAdapter()
        adapterStory.setOnItemClick(object : ListStoryAdapter.OnItemClicked{
            override fun onItemSelect(data: DetailStory) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USER, data)
                    startActivity(it)
                }
            }
        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        // Binding
        binding.apply {
            rvUserStories.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUserStories.setHasFixedSize(true)
            rvUserStories.adapter = adapterStory
        }

        viewModel.setAllStories("Bearer ${session.ambilToken().toString()}",0,10)
        viewModel.getAllStories().observe(this@MainActivity){
            if (it != null){
                adapterStory.setUserList(it as ArrayList<DetailStory>)
            }
        }

        binding.btFabPlus.setOnClickListener{
            Intent(this@MainActivity, AddStoryActivity::class.java).also {
                startActivity(it)
            }
        }

    }

//    private fun showSelectedUser(story: DetailStory) {
//        val move = Intent(this, DetailActivity::class.java)
//        move.putExtra(DetailActivity.EXTRA_USER, story)
//        startActivity(move)
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu -> {
                Intent(this@MainActivity, LoginActivity::class.java).also {
                    startActivity(it)
                    session.clearData()
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}