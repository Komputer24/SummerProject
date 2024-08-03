package com.taskchecker.scrambleapp

data class TaskState(
    val tasks: List<Task> = emptyList(),
    val text: String = "",
    val isAddingTask: Boolean = false,
    val sortType: SortType = SortType.NAME

)