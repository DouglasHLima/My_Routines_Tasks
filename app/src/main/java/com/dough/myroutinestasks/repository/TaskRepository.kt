package com.dough.myroutinestasks.repository

import androidx.annotation.WorkerThread
import com.dough.myroutinestasks.data.CardTask
import com.dough.myroutinestasks.data.CardTaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: CardTaskDao) {

    val allCards: Flow<List<CardTask>> = taskDao.getAlphabetizedCardTask()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cardTask: CardTask){
        taskDao.insert(cardTask)
    }
}