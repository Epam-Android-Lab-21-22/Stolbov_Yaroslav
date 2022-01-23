package com.stolbov.emptyprojectstolbov.ui.main.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stolbov.emptyprojectstolbov.R

class MainAdapter(private val mainViewModel: RecyclerViewModel): RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val idTextView: TextView = itemView.findViewById(R.id.item_id)
        val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        val performerTextView: TextView = itemView.findViewById(R.id.item_performer)
        val coverImageView: ImageView = itemView.findViewById(R.id.item_cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_item, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = mainViewModel.songsMap.value!![position + 1]!!
        holder.idTextView.text = item.id
        holder.titleTextView.text = item.title
        holder.performerTextView.text = item.performer
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(DIALOG_KEY, holder.adapterPosition)
            it.findNavController().navigate(R.id.action_recyclerFragment_to_songFragment, bundle)
        }
        Picasso.get().load(item.coverUrl)
            .placeholder(R.drawable.cover_default)
            .error(R.drawable.cover_default)
            .into(holder.coverImageView)
    }

    override fun getItemCount(): Int {
        return mainViewModel.songsMap.value!!.size
    }



    companion object{
        val DIALOG_KEY = "KEY"
    }
}