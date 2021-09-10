package com.dough.myroutinestasks.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CardTaskDao {

    @Query("SELECT * FROM card_table ORDER BY cardTitle ASC")
    fun getAlphabetizedCardTask(): Flow<List<CardTask>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(cardTask: CardTask)

    @Query("DELETE FROM card_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateTask(task: CardTask)


}