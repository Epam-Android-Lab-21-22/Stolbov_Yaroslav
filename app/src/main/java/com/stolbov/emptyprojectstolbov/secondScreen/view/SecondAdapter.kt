package com.stolbov.emptyprojectstolbov.secondScreen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stolbov.emptyprojectstolbov.data.ItemsViewModel
import com.stolbov.emptyprojectstolbov.databinding.TableItemBinding

class SecondAdapter(private val itemsViewModel: ItemsViewModel): RecyclerView.Adapter<SecondAdapter.SecondViewHolder>() {


    class SecondViewHolder(viewBinding: TableItemBinding): RecyclerView.ViewHolder(viewBinding.root)
    var cats = itemsViewModel.countCatsSecondActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        return SecondViewHolder(
            TableItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return cats
    }

}