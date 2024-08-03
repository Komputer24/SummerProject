package com.taskchecker.scrambleapp

sealed interface TaskEvent{
    object SaveTask: TaskEvent
    data class SetText(val text: String): TaskEvent
    data class DeleteTask(val task: Task): TaskEvent
    data object ShowDialog: TaskEvent
    data object HideDialog: TaskEvent
    data class SortTasks(val sortType: SortType): TaskEvent

}