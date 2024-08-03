package com.taskchecker.scrambleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel (
    private val dao: TaskDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.NAME)
    private val _tasks = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.NAME -> dao.getTasks()
                SortType.DATE -> dao.getTasks()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(TaskState())

    val state = combine(_state, _sortType, _tasks) { state, sortType, tasks ->
        state.copy(
            tasks = tasks,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskState())



    fun onEvent(event: TaskEvent) {
        when (event) {
            is TaskEvent.DeleteTask -> {
                viewModelScope.launch {
                    dao.deleteTask(event.task)
                }
            }

            TaskEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingTask = false
                ) }
            }
            TaskEvent.SaveTask -> {
                val text = state.value.text
                if (text.isBlank()) {
                    return
                }
                val task = Task(
                    text = text
                )
                viewModelScope.launch {
                    dao.upsertTask(task)
                }
                _state.update { it.copy(
                    isAddingTask = false,
                    text = ""
                    )
                }
            }
            is TaskEvent.SetText -> {
                _state.update { it.copy(
                    text = event.text
                ) }
            }
            TaskEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingTask = true
                )}
            }
            is TaskEvent.SortTasks -> {
                _sortType.value = event.sortType
            }
        }
    }
}