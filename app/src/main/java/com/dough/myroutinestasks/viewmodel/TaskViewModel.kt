package com.dough.myroutinestasks.viewmodel

import androidx.lifecycle.*
import com.dough.myroutinestasks.data.CardTask
import com.dough.myroutinestasks.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository): ViewModel() {

    val allTasks: LiveData<List<CardTask>> = repository.allCards.asLiveData()


    fun insert(cardTask: CardTask) = viewModelScope.launch {
        repository.insert(cardTask)
    }
}
class CardTaskViewModelFactory(private val repository: TaskRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }

}

