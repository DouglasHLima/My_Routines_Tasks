package com.dough.myroutinestasks.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CardTaskDao {

    @Query("SELECT * FROM card_table ORDER BY cardTitle ASC")
    fun getAlphabetizedCardTask(): Flow<List<CardTask>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cardTask: CardTask)

    @Query("DELETE FROM card_table")
    suspend fun deleteAll()

}