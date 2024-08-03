package com.taskchecker.scrambleapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.reflect.Modifier

@Composable
fun TaskScreen(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit,
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(TaskEvent.ShowDialog)
            }){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add task"
                )
            }
    }){ padding ->
        LazyColumn (
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            item{
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "${tasks.text} Tasks",
                        fontSize = 30.sp
                    )
                    Button(
                        onClick = { onRemove() },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00BFFF)
                        ),
                    ) {
                        Text(
                            text = "X",
                            style = TextStyle(fontSize = 15.sp)
                        )
                    }
                }
            }
        }

    }
}