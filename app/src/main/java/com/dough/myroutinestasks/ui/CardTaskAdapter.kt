package com.dough.myroutinestasks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dough.myroutinestasks.MainActivity
import com.dough.myroutinestasks.data.CardTask
import com.dough.myroutinestasks.databinding.CardTaskCardviewBinding
import com.dough.myroutinestasks.ui.CardTaskAdapter.*
import com.dough.myroutinestasks.viewmodel.CardTaskViewModelFactory
import com.dough.myroutinestasks.viewmodel.TaskViewModel

class CardTaskAdapter(
    private val listener: OnItemClickListener
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

    }

    inner class CardTaskViewHolder(val binding: CardTaskCardviewBinding):RecyclerView.ViewHolder(binding.root) {


        init {
            binding.apply {
                checkbox.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val task = getItem(position)
                        listener.onCheckBoxClick(task, checkbox.isChecked)
                    }
                }
            }
        }


        fun bind(cardTask: CardTask) {
            binding.tvTitle.text = cardTask.cardTitle
            binding.tvDate.text = cardTask.cardDate
            binding.tvHour.text = cardTask.cardHour
            binding.tvDescription.text = cardTask.cardDescription
            binding.checkbox.isChecked = cardTask.checked

        }

    }

    interface OnItemClickListener{
        fun onCheckBoxClick(task: CardTask, isChecked: Boolean)


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


