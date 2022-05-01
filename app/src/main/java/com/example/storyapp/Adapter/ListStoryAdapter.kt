package com.example.storyapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storyapp.Datauser.DetailStory
import com.example.storyapp.Datauser.Story
import com.example.storyapp.databinding.ItemRowBinding

class ListStoryAdapter: RecyclerView.Adapter<ListStoryAdapter.ListViewHolder>() {
    private val listStory = ArrayList<DetailStory>()
    private var onItemClick: OnItemClicked? = null

    inner class ListViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DetailStory) {
            binding.root.setOnClickListener {
                onItemClick?.onItemSelect(data)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(data.photoUrl)
                    .into(imgStory)
                tvPengupload.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listStory[position])
    }

    fun setOnItemClick(itemClick: OnItemClicked) {
        this.onItemClick = itemClick
    }

    fun setUserList(users: ArrayList<DetailStory>) {
        listStory.clear()
        listStory.addAll(users)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listStory.size

    interface OnItemClicked {
        fun onItemSelect(data: DetailStory)
    }

}