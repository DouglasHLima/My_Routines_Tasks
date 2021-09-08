package com.dough.myroutinestasks.repository

import androidx.annotation.WorkerThread
import com.dough.myroutinestasks.data.*
import kotlinx.coroutines.flow.Flow

class CardTaskRepository(
    private val cardTaskDao: CardTaskDao,
){

    val allCards: Flow<List<CardTask>> = cardTaskDao.getAlphabetizedCardTask()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCardTask(cardTask: CardTask) {
        cardTaskDao.insertCard(cardTask)
    }
    @Suppress
    @WorkerThread
    suspend fun updateTask(task: CardTask){
        cardTaskDao.updateTask(task)
    }

}