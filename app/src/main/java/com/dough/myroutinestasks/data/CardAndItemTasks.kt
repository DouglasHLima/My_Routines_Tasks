package com.dough.myroutinestasks.data

import androidx.room.Embedded
import androidx.room.Relation

data class CardAndItemTasks(
   @Embedded
    val card: CardTask,
    @Relation(
        parentColumn = "id",
        entityColumn = "cardRef"
    )
    val itens: List<ItemTask>
)
