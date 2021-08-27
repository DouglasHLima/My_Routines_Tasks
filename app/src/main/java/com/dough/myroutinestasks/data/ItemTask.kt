package com.dough.myroutinestasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_list")
data class ItemTask(
    @PrimaryKey
        val id: Int = 0,
        val itemTitle: String,
        val checked: Boolean
)
