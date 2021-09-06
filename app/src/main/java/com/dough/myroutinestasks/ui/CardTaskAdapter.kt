package com.dough.myroutinestasks.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dough.myroutinestasks.data.CardAndItemTasks
import com.dough.myroutinestasks.data.ItemTask
import com.dough.myroutinestasks.databinding.CardTaskCardviewBinding
import com.dough.myroutinestasks.ui.CardTaskAdapter.*

class CardTaskAdapter(
    private val context: Context,
    private val list: LiveData<List<CardAndItemTasks>>
) : androidx.recyclerview.widget.ListAdapter<CardAndItemTasks, CardTaskViewHolder>(CardTaskComparator()){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardTaskCardviewBinding.inflate(inflater,parent,false)
        return CardTaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardTaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

        var isVisible = current.card.visibility
        holder.binding.cvCardTaskDescription.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.binding.rvCardTaskItems.visibility = if (isVisible) View.VISIBLE else View.GONE

        holder.binding.cvCardTask.setOnClickListener {
            current.card.visibility = !current.card.visibility
            notifyItemChanged(position)
        }


        setCallRecyclerItemTask(holder.binding.rvCardTaskItems, current.itens)

    }

        fun setCallRecyclerItemTask(recyclerView: RecyclerView, itemTask: List<ItemTask>) {
        val itemRecyclerAdapter = ItemTaskAdapter(itemTask)
        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL, false)
        recyclerView.adapter = itemRecyclerAdapter
    }


    class CardTaskViewHolder(val binding: CardTaskCardviewBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(cardTask: CardAndItemTasks) {
            binding.cvCardTaskTitle.text = cardTask.card.cardTitle
            binding.cvCardTaskDescription.text = cardTask.card.cardDescription
            binding.tvCardTaskDate.text = cardTask.card.cardDate
            binding.tvCardTaskHour.text = cardTask.card.cardHour
            binding.rvCardTaskItems.adapter = ItemTaskAdapter(cardTask.itens)

        }



    }


    class CardTaskComparator : DiffUtil.ItemCallback<CardAndItemTasks>(){


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



