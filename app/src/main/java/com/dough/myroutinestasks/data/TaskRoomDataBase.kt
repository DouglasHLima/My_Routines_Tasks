package com.dough.myroutinestasks.data

import android.content.ClipData
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(CardTask::class), version = 1, exportSchema = false)
public abstract class TaskRoomDataBase : RoomDatabase() {

    abstract fun taskDao(): CardTaskDao

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
                    populateDatabase(database.taskDao())
                }
            }
        }

        suspend fun populateDatabase(taskDao: CardTaskDao){
            taskDao.deleteAll()

            val taskList1 : MutableList<ItemTask> = ArrayList()
            taskList1.add(ItemTask(1,"pastel", true))
            val task = CardTask(1,"ola boy","fiz pipoca","10/20/20","115586")
            taskDao.insert(task)

        }
    }

}