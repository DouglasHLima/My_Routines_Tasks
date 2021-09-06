package com.dough.myroutinestasks.repository

import androidx.annotation.WorkerThread
import com.dough.myroutinestasks.data.*
import kotlinx.coroutines.flow.Flow

class CardTaskRepository(
    private val cardTaskDao: CardTaskDao,
    private val itemTasksDao: ItemTaskDao) {

    val allCards: Flow<List<CardTask>> = cardTaskDao.getAlphabetizedCardTask()
    val allItemTasks: Flow<List<ItemTask>> = itemTasksDao.getItens()
    val allCardAndItemTasks: Flow<List<CardAndItemTasks>> = cardTaskDao.getCardWithItemTask()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCardTask(cardTask: CardTask){
        cardTaskDao.insertCard(cardTask)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertItensTasks(itemTask: ItemTask){
        itemTasksDao.insertItem(itemTask)
    }
}