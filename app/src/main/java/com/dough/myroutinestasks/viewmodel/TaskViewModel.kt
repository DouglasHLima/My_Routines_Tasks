package com.dough.myroutinestasks.viewmodel

import androidx.lifecycle.*
import com.dough.myroutinestasks.data.CardAndItemTasks
import com.dough.myroutinestasks.data.CardTask
import com.dough.myroutinestasks.data.ItemTask
import com.dough.myroutinestasks.repository.CardTaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repositoryCard: CardTaskRepository): ViewModel() {

    val allTasks: LiveData<List<CardTask>> = repositoryCard.allCards.asLiveData()
    val allCardsAndItemTasks: LiveData<List<CardAndItemTasks>> = repositoryCard.allCardAndItemTasks.asLiveData()


    fun insertCard(cardTask: CardTask) = viewModelScope.launch {
        repositoryCard.insertCardTask(cardTask)
    }

    fun insertItens(itemTask: List<ItemTask>) = viewModelScope.launch{

        itemTask.forEach {
            repositoryCard.insertItensTasks(it)
        }
    }

    fun insertAll(cardTask: CardTask, itemTask: List<ItemTask>) = viewModelScope.launch {
        repositoryCard.insertCardTask(cardTask)
        itemTask.forEach {
            repositoryCard.insertItensTasks(it)
        }


    }


}
class CardTaskViewModelFactory(private val repositoryCard: CardTaskRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repositoryCard) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }

}

