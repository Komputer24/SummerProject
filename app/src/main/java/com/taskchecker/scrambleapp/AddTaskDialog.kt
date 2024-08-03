package com.taskchecker.scrambleapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.lang.reflect.Modifier

@Composable
fun addTaskDialog(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        onDismissRequest = {},
        title = {Text(text = "Add Task")},
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.text,
                    onValueChange = {
                        onEvent(TaskEvent.SetText(it))
                    },
                    placeholder = {
                        Text(text = "Task")
                    }
                )
            }
            )`
}

