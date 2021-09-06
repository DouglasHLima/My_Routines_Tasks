package com.dough.myroutinestasks.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemTaskDao {
    @Query("SELECT * FROM item_list")
    fun getItens(): Flow<List<ItemTask>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(itemTask: ItemTask)

    @Query("DELETE FROM item_list")
    suspend fun deleteAllItem()
}