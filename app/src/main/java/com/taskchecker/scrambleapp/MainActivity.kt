// Change Version
package com.taskchecker2.scrambleapp

import androidx.compose.ui.unit.sp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.taskchecker.scrambleapp.ui.theme.ScrambleAppTheme
import kotlin.math.roundToInt
import java.text.DateFormat
import java.util.Calendar
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment

data class Task(
    val date: String,
    val name: String
)

@Composable
private fun DraggableRow(
    text: String,
    onRemove: () -> Unit
) {
    var offsetY by remember { mutableStateOf(0f) }
    Row(
        modifier = Modifier
            .offset { IntOffset(0, offsetY.roundToInt()) }
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF00BFFF))
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "* " + text,
            modifier = Modifier.weight(1f).padding(end = 16.dp),
            color = Color(0xFFFFFFFF),
            style = TextStyle(fontSize = 30.sp)
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
                style = TextStyle(fontSize = 30.sp)
            )
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>, onRemove: (Task) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(tasks) { index, task ->
            if (index == 0 || task.date != tasks[index - 1].date) {
                Text(
                    text = task.date,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    color = Color(0xFF000000)
                )
            }
            DraggableRow(
                text = task.name,
                onRemove = { onRemove(task) }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrambleAppTheme {
                var name by remember { mutableStateOf("") }
                var tasks by remember { mutableStateOf(listOf<Task>()) }
                val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().time)

                Box(
                    modifier = Modifier.fillMaxSize().background(Color(0xFF00BFFF))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "TO DO LIST:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                modifier = Modifier
                                    .padding(top = 30.dp, start = 10.dp),
                                color = Color(0xFF0000FF)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(7.dp)
                                .background(Color(0xFF00BFFF))
                        ) {}
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(7.dp)
                                .background(Color.White)
                        ) {}
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(7.dp)
                                .background(Color(0xFF00BFFF))
                        ) {}
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            OutlinedTextField(
                                value = name,
                                onValueChange = { text -> name = text },
                                modifier = Modifier
                                    .fillMaxWidth(0.75f)
                                    .padding(10.dp),
                            )
                            Button(
                                onClick = {
                                    if (name.isNotBlank()) {
                                        tasks = listOf(Task(currentDate, name)) + tasks
                                        name = ""
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF0000ff)
                                ),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .offset(x = -10.dp, y = 0.dp)
                            ) {
                                Text(
                                    text = "+",
                                    style = TextStyle(fontSize = 33.sp),
                                    color = Color(0xFFFFFFFF)
                                )
                            }
                        }
                        TaskList(
                            tasks = tasks,
                            onRemove = { task ->
                                tasks = tasks.toMutableList().apply {
                                    remove(task)
                                }
                            }
                        )
                    }
                    // Footer
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF0000FF))
                            .padding(10.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        Text(
                            text = "Developer: Kaleab Beteselassie",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
