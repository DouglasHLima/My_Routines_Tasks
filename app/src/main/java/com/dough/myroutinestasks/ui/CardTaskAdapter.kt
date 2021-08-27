package com.dough.myroutinestasks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dough.myroutinestasks.R
import com.dough.myroutinestasks.data.CardTask
import com.dough.myroutinestasks.ui.CardTaskAdapter.*

class CardTaskAdapter : androidx.recyclerview.widget.ListAdapter<CardTask, CardTaskViewHolder>(CardTaskComparator()){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTaskViewHolder {
        return CardTaskViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CardTaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.cardTitle)
    }



    class CardTaskViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val cardTaskItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(cardTitle: String) {
            cardTaskItemView.text = cardTitle

        }
        companion object {
            fun create(parent: ViewGroup): CardTaskViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item,parent,false)
                return CardTaskViewHolder(view)
            }
        }

    }


    class CardTaskComparator : DiffUtil.ItemCallback<CardTask>(){
        override fun areItemsTheSame(oldItem: CardTask, newItem: CardTask): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CardTask, newItem: CardTask): Boolean {
            return oldItem.id == newItem.id
        }

    }


}



