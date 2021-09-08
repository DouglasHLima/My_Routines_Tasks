package com.dough.myroutinestasks.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dough.myroutinestasks.aplication.CardTaskAplication
import com.dough.myroutinestasks.data.CardTask
import com.dough.myroutinestasks.databinding.CardTaskCardviewBinding
import com.dough.myroutinestasks.ui.CardTaskAdapter.*
import com.dough.myroutinestasks.viewmodel.CardTaskViewModelFactory
import com.dough.myroutinestasks.viewmodel.TaskViewModel

class CardTaskAdapter(
    private val context: Context,
) : androidx.recyclerview.widget.ListAdapter<CardTask, CardTaskViewHolder>(CardTaskComparator()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardTaskCardviewBinding.inflate(inflater,parent,false)
        return CardTaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardTaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

        var isVisible = current.visibility
        holder.binding.tvDescription.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.binding.cvCardTask.setOnClickListener {
            current.visibility = !current.visibility
            notifyItemChanged(position)
        }
        holder.binding.checkbox.setOnClickListener {
            current.checked = !current.checked
        }
    }

    class CardTaskViewHolder(val binding: CardTaskCardviewBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(cardTask: CardTask) {
            binding.tvTitle.text = cardTask.cardTitle
            binding.tvDate.text = cardTask.cardDate
            binding.tvHour.text = cardTask.cardHour
            binding.tvDescription.text = cardTask.cardDescription
            binding.checkbox.isChecked = cardTask.checked

        }



    }


    class CardTaskComparator : DiffUtil.ItemCallback<CardTask>(){


        override fun areItemsTheSame(
            oldItem: CardTask,
            newItem: CardTask,
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: CardTask,
            newItem: CardTask,
        ): Boolean {
            return  oldItem.id == newItem.id
        }

    }


}



