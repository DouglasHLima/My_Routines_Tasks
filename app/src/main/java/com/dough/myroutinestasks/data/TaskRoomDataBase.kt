package com.dough.myroutinestasks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [CardTask::class,ItemTask::class], version = 1, exportSchema = false)
public abstract class TaskRoomDataBase : RoomDatabase() {

    abstract fun cardTaskDao(): CardTaskDao
    abstract fun itemTaskDao(): ItemTaskDao

    companion object{
        @Volatile
        private var INSTANCE: TaskRoomDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TaskRoomDataBase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDataBase::class.java,
                    "task_database"
                )
                    .addCallback(TaskDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class TaskDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.cardTaskDao(),database.itemTaskDao())
                }
            }
        }

        suspend fun populateDatabase(cardTaskDao: CardTaskDao, itemTaskDao: ItemTaskDao){
            cardTaskDao.deleteAll()
            itemTaskDao.deleteAllItem()

        }
    }

}