package com.dough.myroutinestasks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dough.myroutinestasks.data.CardAndItemTasks
import com.dough.myroutinestasks.data.ItemTask
import com.dough.myroutinestasks.databinding.TaskItemBinding

class ItemTaskAdapter(private val itemTasks: List<ItemTask>): androidx.recyclerview.widget.ListAdapter<CardAndItemTasks,ItemTaskAdapter.ItemTaskViewHolder>(ItemTaskComparator()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(inflater, parent, false)
        return ItemTaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTaskViewHolder, position: Int) {
        holder.bind(itemTasks[position])
        val current = itemTasks[position]

        holder.binding.checkboxItem.setOnClickListener {
            current.checked = holder.binding.checkboxItem.isChecked
        }
    }


    class ItemTaskViewHolder(val binding: TaskItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(itemTasks: ItemTask){
            binding.checkboxItem.text = itemTasks.itemTitle
            binding.checkboxItem.isChecked = itemTasks.checked
        }

    }




    class ItemTaskComparator : DiffUtil.ItemCallback<CardAndItemTasks>(){


        override fun areItemsTheSame(
            oldItem: CardAndItemTasks,
            newItem: CardAndItemTasks,
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: CardAndItemTasks,
            newItem: CardAndItemTasks,
        ): Boolean {
            return  oldItem.card.id == newItem.card.id
        }

    }
}

