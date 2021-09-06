package com.dough.myroutinestasks.aplication

import android.app.Application
import com.dough.myroutinestasks.data.TaskRoomDataBase
import com.dough.myroutinestasks.repository.CardTaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CardTaskAplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { TaskRoomDataBase.getDatabase(this, applicationScope) }
    val repository by lazy { CardTaskRepository(database.cardTaskDao(),database.itemTaskDao()) }
}