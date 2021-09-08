package com.dough.myroutinestasks.viewmodel

import androidx.lifecycle.*
import com.dough.myroutinestasks.data.CardTask
import com.dough.myroutinestasks.repository.CardTaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repositoryCard: CardTaskRepository): ViewModel() {

    val allTasks: LiveData<List<CardTask>> = repositoryCard.allCards.asLiveData()

    fun insertCard(cardTask: CardTask) = viewModelScope.launch {
        repositoryCard.insertCardTask(cardTask)
    }

    fun updateTask(cardTask: CardTask) = viewModelScope.launch {
        repositoryCard.updateTask(cardTask)
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

