package com.example.scrambleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrambleapp.ui.theme.ScrambleAppTheme
import kotlin.math.roundToInt
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.zIndex
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
private fun DraggableRow(
    text: String,
    onRemove: () -> Unit
) {
    var offsetY by remember { mutableStateOf(0f) }
    var initialY by remember { mutableStateOf(0f) }
    Row(
        modifier = Modifier
            .zIndex(if (offsetY != initialY) 1f else 0f)
            .offset { IntOffset(0, offsetY.roundToInt()) }
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF7A9E9F))
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = text,
            modifier = Modifier.weight(1f).padding(end = 16.dp)
        )
        Button(
            onClick = { onRemove() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFE5F55)
            ),
            ) {
                Text(
                text = "X",
                style = TextStyle(fontSize = 15.sp)
            )
        }
    }
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        var dateList by remember { mutableStateOf(listOf<String>()) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEEF5DB))
        )
            ScrambleAppTheme {

                var name by remember { mutableStateOf("") }
                var names by remember { mutableStateOf(listOf<String>()) }
                var miniText1 by remember { mutableStateOf("") }
                var miniText2 by remember { mutableStateOf("") }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "ToDo List:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            modifier = Modifier
                                .padding(top = 30.dp, start = 10.dp),
                                color = Color(0xFF4F6367)
                        )
                    }
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
                                    names = names + name
                                    name = ""
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4F6367)
                            ),
                            modifier = Modifier
                                .padding(10.dp)
                                .offset(x = -10.dp, y = 0.dp)
                        ) {
                            Text(
                                text = "+",
                                style = TextStyle(fontSize = 33.sp),
                                color = Color.White
                            )
                        }
                    }
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                    ) {
//                        OutlinedTextField(
//                            value = miniText1,
//                            onValueChange = { text -> miniText1 = text },
//                            modifier = Modifier
//                                .weight(1f)
//                                .padding(10.dp),
//                        )
////                        Button(
////                            onClick = {
////                                // Do something with miniText1 and miniText2
////                            },
////                            shape = RoundedCornerShape(8.dp),
////                            colors = ButtonDefaults.buttonColors(
////                                containerColor = Color(0xFF4F6367)
////                            ),
////                            modifier = Modifier
////                                .padding(10.dp)
////                        ) {
////                            Text(
////                                text = "Switch",
////                                style = TextStyle(fontSize = 15.sp),
////                                color = Color.White
////                            )
////                        }
//                        OutlinedTextField(
//                            value = miniText2,
//                            onValueChange = { text -> miniText2 = text },
//                            modifier = Modifier
//                                .weight(1f)
//                                .padding(10.dp),
//                        )
//                    }
                    LazyColumn (modifier = Modifier.fillMaxSize()){
                        itemsIndexed(names) { index, currentName ->
                            GetDateAndTime("Android")
                            DraggableRow(
                                text = currentName,
                                onRemove = {
                                    names = names.toMutableList().apply {
                                        removeAt(index)
                                    }
                                }
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth() // Fill the width of the Row
                                        .height(5.dp) // Set a fixed height for the Box
                                        .background(
                                            color = Color(0xFFEEF5DB),
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun GetDateAndTime(date: String){
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar)

    Row(){
        Text(text = "$dateFormat")
    }
}


