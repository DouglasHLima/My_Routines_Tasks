package com.dough.myroutinestasks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
    data class CardTask(
    @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val cardTitle: String,
        val cardDescription: String,
        val cardDate: String,
        val cardHour: String,
        var checked: Boolean

    ){
        var visibility: Boolean = false

}

