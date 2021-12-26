package com.stolbov.emptyprojectstolbov.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.stolbov.emptyprojectstolbov.ItemsViewModel
import com.stolbov.emptyprojectstolbov.R
import com.stolbov.emptyprojectstolbov.data.MyItem
import com.stolbov.emptyprojectstolbov.databinding.FirstItemLayoutBinding
import com.stolbov.emptyprojectstolbov.databinding.SecondItemLayoutBinding
import com.stolbov.emptyprojectstolbov.databinding.ThirdItemLayoutBinding

class MyAdapter(private val itemsViewModel: ItemsViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_EXCEPTION = "unknown viewType"

    private val items: MutableList<MyItem> = mutableListOf()

    class FirstViewHolder(
        private val viewBinding: FirstItemLayoutBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: MyItem.FirstItem){
            viewBinding.apply {
                itemTextviewId.text = item.id
                itemTextviewTittle.text = item.textItem
            }
        }
    }

    class SecondViewHolder(
        viewBinding: SecondItemLayoutBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)

    inner class ThirdViewHolder(
        viewBinding: ThirdItemLayoutBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ViewType.FIRST_ITEM_TYPE.ordinal -> {
                FirstViewHolder(
                    FirstItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            ViewType.SECOND_ITEM_TYPE.ordinal -> {
                SecondViewHolder(
                    SecondItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            ViewType.THIRD_ITEM_TYPE.ordinal -> {
                ThirdViewHolder(
                    ThirdItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw Exception(VIEW_TYPE_EXCEPTION)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FirstViewHolder -> {holder.bind(items[position] as MyItem.FirstItem)}
            is SecondViewHolder -> {}
            is ThirdViewHolder -> {
                holder.itemView.findViewById<ImageButton>(R.id.delete_button_main).setOnClickListener {
                    it.isClickable = false
                    deleteItem(holder)
                }
            }
        }

    }

    private fun deleteItem(holder: ThirdViewHolder) {
        items.removeAt(holder.adapterPosition)
        itemsViewModel.items.removeAt(holder.adapterPosition)
        notifyItemRemoved(holder.adapterPosition)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is MyItem.FirstItem -> ViewType.FIRST_ITEM_TYPE.ordinal
            is MyItem.SecondItem -> ViewType.SECOND_ITEM_TYPE.ordinal
            is MyItem.ThirdItem -> ViewType.THIRD_ITEM_TYPE.ordinal
        }
    }

    fun updateItems(newItems: List<MyItem>){
        items.clear()
        items.addAll(newItems)
        itemsViewModel.items.clear()
        itemsViewModel.items.addAll(items)
        notifyDataSetChanged()
    }



    enum class ViewType{
        FIRST_ITEM_TYPE,
        SECOND_ITEM_TYPE,
        THIRD_ITEM_TYPE
    }
}